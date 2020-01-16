package pers.dc.ols.service.center;

import pers.dc.ols.pojo.OrderItem;
import pers.dc.ols.pojo.bo.center.OrderCommentsBO;

import java.util.List;

public interface CenterCommentService {
    List<OrderItem> queryPendingComments(String orderId);
    void saveComments(String userId, String orderId, List<OrderCommentsBO> commentList);
}
