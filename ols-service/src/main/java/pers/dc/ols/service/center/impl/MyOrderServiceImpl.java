package pers.dc.ols.service.center.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.dc.ols.mapper.CustomOrderMapper;
import pers.dc.ols.mapper.OrderMapper;
import pers.dc.ols.pojo.OrderExample;
import pers.dc.ols.pojo.vo.MyOrdersVO;
import pers.dc.ols.pojo.vo.center.OrderStatusCountsVO;
import pers.dc.ols.pojo.vo.center.PreOrderStatusCountsVO;
import pers.dc.ols.service.center.MyOrderService;
import pers.dc.ols.service.common.PagingService;
import pers.dc.ols.utils.PagedGridResult;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MyOrderServiceImpl extends PagingService implements MyOrderService {

    @Resource
    private CustomOrderMapper customOrderMapper;

    @Resource
    private OrderMapper orderMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<MyOrdersVO> orders = customOrderMapper.queryMyOrders(userId, orderStatus);
        return getResult(orders, page, pageSize);
    }

    @Override
    public boolean userHasOrder(String userId, String orderId) {
        OrderExample oe = new OrderExample();
        OrderExample.Criteria criteria = oe.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andIdEqualTo(orderId);
        return orderMapper.selectByExample(oe) != null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public OrderStatusCountsVO queryOrderStatusCounts(String userId) {
        List<PreOrderStatusCountsVO> list = customOrderMapper.queryStatusCounts(userId);
        OrderStatusCountsVO result = new OrderStatusCountsVO();
        result.initOrderStatusCountsVO(list);
        return result;
    }
}
