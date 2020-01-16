package pers.dc.ols.service.center;

import org.n3r.idworker.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.dc.ols.enums.YesOrNo;
import pers.dc.ols.mapper.CustomCommentMapper;
import pers.dc.ols.mapper.ItemCommentMapper;
import pers.dc.ols.mapper.OrderItemMapper;
import pers.dc.ols.mapper.OrderMapper;
import pers.dc.ols.pojo.ItemComment;
import pers.dc.ols.pojo.OrderItem;
import pers.dc.ols.pojo.OrderItemExample;
import pers.dc.ols.pojo.OrderStatus;
import pers.dc.ols.pojo.bo.center.OrderCommentsBO;
import pers.dc.ols.service.OrderService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CenterCommentServiceImpl implements CenterCommentService {

    @Resource private OrderItemMapper orderItemMapper;
    @Resource private OrderMapper orderMapper;
    @Resource private CustomCommentMapper customCommentMapper;
    @Resource private OrderService orderService;
    @Resource private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<OrderItem> queryPendingComments(String orderId) {
        if (orderMapper.selectByPrimaryKey(orderId).getIsComment() == YesOrNo.Yes.type)
            return null;
        OrderItemExample ex = new OrderItemExample();
        ex.createCriteria().andOrderIdEqualTo(orderId);
        return orderItemMapper.selectByExample(ex);
    }

    @Transactional
    @Override
    public void saveComments(String userId, String orderId, List<OrderCommentsBO> commentList) {

        for (OrderCommentsBO comment : commentList)
            comment.setId(sid.nextShort());
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("commentList", commentList);
        customCommentMapper.doComments(map);

        orderService.setOrderCommented(orderId);
    }
}
