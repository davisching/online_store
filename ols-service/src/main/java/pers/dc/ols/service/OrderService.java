package pers.dc.ols.service;

import pers.dc.ols.pojo.bo.OrderCreateBO;

public interface OrderService {
    String createOrder(OrderCreateBO orderCreateBO);
}
