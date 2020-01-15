package pers.dc.ols.service;

import pers.dc.ols.pojo.OrderStatus;
import pers.dc.ols.pojo.bo.OrderCreateBO;
import pers.dc.ols.pojo.vo.OrderVO;

public interface OrderService {
    OrderVO createOrder(OrderCreateBO orderCreateBO);
    void updateOrderStatus(String orderId, Integer orderStatusNum);
    OrderStatus queryOrderStatusByOrderId(String orderId);
    void closeAllUnpaidOrder();
    void closeOneOrder(OrderStatus orderStatus);
}
