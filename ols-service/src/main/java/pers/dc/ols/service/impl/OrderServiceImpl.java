package pers.dc.ols.service.impl;

import org.n3r.idworker.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.dc.ols.enums.OrderStatusEnum;
import pers.dc.ols.enums.YesOrNo;
import pers.dc.ols.mapper.*;
import pers.dc.ols.pojo.*;
import pers.dc.ols.pojo.bo.OrderCreateBO;
import pers.dc.ols.pojo.vo.ShopCartItemVO;
import pers.dc.ols.service.ItemService;
import pers.dc.ols.service.OrderService;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource private Sid sid;

    @Resource UserAddressMapper userAddressMapper;
    @Resource ItemSpecMapper itemSpecMapper;
    @Resource ItemService itemService;

    @Resource OrderMapper orderMapper;
    @Resource OrderItemMapper orderItemMapper;
    @Resource OrderStatusMapper orderStatusMapper;

    @Transactional
    @Override
    public String createOrder(OrderCreateBO orderCreateBO) {

        String userId = orderCreateBO.getUserId();
        String addressId = orderCreateBO.getAddressId();
        String itemSpecIds = orderCreateBO.getItemSpecIds();
        Integer paymentMethod = orderCreateBO.getPayMethod();
        String leftMsg = orderCreateBO.getLeftMsg();
        Integer postAmount = 0;

        UserAddress address =  userAddressMapper.selectByPrimaryKey(addressId);

        Order order = new Order();
        String orderId = sid.nextShort();

        String[] specIds = itemSpecIds.split(",");
        Integer totalAmount = 0;
        Integer realPayAmount = 0;

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

        return orderId;
    }

    private String initAddress(UserAddress userAddress) {
        StringBuilder sb = new StringBuilder();
        sb.append(userAddress.getProvince()).append(" ")
                .append(userAddress.getCity()).append(" ")
                .append(userAddress.getDistrict()).append(" ")
                .append(userAddress.getDetail());
        return sb.toString();
    }
}
