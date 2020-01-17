package pers.dc.ols.mapper;

import pers.dc.ols.pojo.vo.MyOrdersVO;
import pers.dc.ols.pojo.vo.center.PreOrderStatusCountsVO;

import java.util.List;

public interface CustomOrderMapper {
    List<MyOrdersVO> queryMyOrders(String userId, Integer orderStatus);
    List<PreOrderStatusCountsVO> queryStatusCounts(String userId);
    Integer getCommentedCount(String userId);
}