package pers.dc.ols.service.impl;

import org.n3r.idworker.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.dc.ols.enums.OrderStatusEnum;
import pers.dc.ols.enums.YesOrNo;
import pers.dc.ols.mapper.*;
import pers.dc.ols.pojo.*;
import pers.dc.ols.pojo.bo.OrderCreateBO;
import pers.dc.ols.pojo.vo.MerchantOrdersVO;
import pers.dc.ols.pojo.vo.OrderVO;
import pers.dc.ols.pojo.vo.ShopCartItemVO;
import pers.dc.ols.service.ItemService;
import pers.dc.ols.service.OrderService;
import pers.dc.ols.utils.DateUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final String RETURN_URL = "https://50082bf7.ngrok.io/ols/orders/notifyOrderPaid";

    @Resource private Sid sid;

    @Resource UserAddressMapper userAddressMapper;
    @Resource ItemSpecMapper itemSpecMapper;
    @Resource ItemService itemService;

    @Resource OrderMapper orderMapper;
    @Resource OrderItemMapper orderItemMapper;
    @Resource OrderStatusMapper orderStatusMapper;

    @Transactional
    @Override
    public OrderVO createOrder(OrderCreateBO orderCreateBO) {

        String userId = orderCreateBO.getUserId();
        String addressId = orderCreateBO.getAddressId();
        String itemSpecIds = orderCreateBO.getItemSpecIds();
        Integer paymentMethod = orderCreateBO.getPayMethod();
        String leftMsg = orderCreateBO.getLeftMsg();
        int postAmount = 0;

        UserAddress address =  userAddressMapper.selectByPrimaryKey(addressId);

        Order order = new Order();
        String orderId = sid.nextShort();

        String[] specIds = itemSpecIds.split(",");
        int totalAmount = 0;
        int realPayAmount = 0;

        for (String specId : specIds) {
            ItemSpec itemSpec = itemSpecMapper.selectByPrimaryKey(specId);
            ShopCartItemVO item = itemService.queryItemsBySpecId(specId).get(0);

            OrderItem orderItem = new OrderItem();
            // TODO 购买个数需要从 redis 中获取
            int buyCounts = 1;
            totalAmount += itemSpec.getPriceNormal() * buyCounts;
            realPayAmount += itemSpec.getPriceDiscount() * buyCounts;

            orderItem.setId(sid.nextShort());
            orderItem.setOrderId(orderId);
            orderItem.setItemId(itemSpec.getItemId());
            orderItem.setItemImg(item.getItemImgUrl());
            orderItem.setItemName(item.getItemName());
            orderItem.setItemSpecId(specId);
            orderItem.setItemSpecName(item.getItemName());
            orderItem.setPrice(itemSpec.getPriceDiscount());
            orderItem.setBuyCounts(buyCounts);
            orderItemMapper.insert(orderItem);

            itemService.decreaseStock(specId, buyCounts);
        }

        order.setId(orderId);
        order.setUserId(userId);
        order.setReceiverName(address.getReceiver());
        order.setReceiverMobile(address.getMobile());
        order.setReceiverAddress(initAddress(address));
        order.setTotalAmount(totalAmount);
        order.setRealPayAmount(realPayAmount);
        order.setPostAmount(postAmount);
        order.setPayMethod(paymentMethod);
        order.setLeftMsg(leftMsg);
        order.setIsComment(YesOrNo.No.type);
        order.setIsDelete(YesOrNo.No.type);
        order.setCreatedTime(new Date());
        order.setUpdatedTime(new Date());
        orderMapper.insert(order);

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        orderStatus.setCreatedTime(new Date());
        orderStatusMapper.insert(orderStatus);

        MerchantOrdersVO merchantOrdersVO = new MerchantOrdersVO();
        merchantOrdersVO.setMerchantOrderId(orderId);
        merchantOrdersVO.setMerchantUserId(userId);
        merchantOrdersVO.setAmount(realPayAmount + postAmount);
        merchantOrdersVO.setPayMethod(paymentMethod);
        merchantOrdersVO.setReturnUrl(RETURN_URL);

        OrderVO orderVO = new OrderVO();
        orderVO.setOrderId(orderId);
        orderVO.setMerchantOrdersVO(merchantOrdersVO);

        return orderVO;
    }

    private String initAddress(UserAddress userAddress) {
        StringBuilder sb = new StringBuilder();
        sb.append(userAddress.getProvince()).append(" ")
                .append(userAddress.getCity()).append(" ")
                .append(userAddress.getDistrict()).append(" ")
                .append(userAddress.getDetail());
        return sb.toString();
    }

    @Transactional
    @Override
    public void updateOrderStatus(String orderId, Integer orderStatusNum) {
        OrderStatus orderStatus = orderStatusMapper.selectByPrimaryKey(orderId);
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(orderStatusNum);

        if (orderStatusNum == OrderStatusEnum.WAIT_RECEIVE.type) {
            orderStatus.setDeliverTime(new Date());
        } else if (orderStatusNum == OrderStatusEnum.CLOSE.type) {
            orderStatus.setCloseTime(new Date());
        } else if (orderStatusNum == OrderStatusEnum.WAIT_DELIVER.type) {
            orderStatus.setPayTime(new Date());
        } else if (orderStatusNum == OrderStatusEnum.SUCCESS.type) {
            orderStatus.setSuccessTime(new Date());
        }

        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public OrderStatus queryOrderStatusByOrderId(String orderId) {
        return orderStatusMapper.selectByPrimaryKey(orderId);
    }

    @Transactional
    @Override
    public void closeAllUnpaidOrder() {
        OrderStatusExample example = new OrderStatusExample();
        OrderStatusExample.Criteria criteria = example.createCriteria();
        criteria.andOrderStatusEqualTo(OrderStatusEnum.WAIT_PAY.type);
        List<OrderStatus> orderStatuses = orderStatusMapper.selectByExample(example);
        Date now = new Date();
        long closing_time = 1000*60*60*2;
        for (OrderStatus orderStatus : orderStatuses) {
            if (now.getTime() - orderStatus.getCreatedTime().getTime() >= closing_time) {
               closeOneOrder(orderStatus);
            }
        }
    }

    @Transactional
    @Override
    public void closeOneOrder(OrderStatus orderStatus) {
        orderStatus.setOrderStatus(OrderStatusEnum.CLOSE.type);
        orderStatus.setCloseTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }

    @Transactional
    @Override
    public void deleteOrder(String orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        order.setIsDelete(YesOrNo.Yes.type);
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public void setOrderCommented(String orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        order.setIsComment(YesOrNo.Yes.type);
        orderMapper.updateByPrimaryKeySelective(order);

        OrderStatus orderStatus = orderStatusMapper.selectByPrimaryKey(orderId);
        orderStatus.setCommentTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }

}
