package pers.dc.ols.mapper;

import pers.dc.ols.pojo.vo.MyOrdersVO;

import java.util.List;

public interface CustomOrderMapper {
    List<MyOrdersVO> queryMyOrders(String userId, Integer orderStatus);
}