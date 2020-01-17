package pers.dc.ols.service.center;

import pers.dc.ols.pojo.OrderItem;
import pers.dc.ols.pojo.bo.center.OrderCommentsBO;
import pers.dc.ols.pojo.vo.center.MyCommentVO;
import pers.dc.ols.utils.PagedGridResult;

import java.util.List;

public interface CenterCommentService {
    List<OrderItem> queryPendingComments(String orderId);
    void saveComments(String userId, String orderId, List<OrderCommentsBO> commentList);
    PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize);
}
