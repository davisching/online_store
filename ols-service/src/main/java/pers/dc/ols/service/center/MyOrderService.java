package pers.dc.ols.service.center;

import pers.dc.ols.pojo.vo.center.OrderStatusCountsVO;
import pers.dc.ols.utils.PagedGridResult;

public interface MyOrderService {
    PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize);
    boolean userHasOrder(String userId, String orderId);
    OrderStatusCountsVO queryOrderStatusCounts(String userId);
}