package pers.dc.ols.controller.center;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.dc.ols.pojo.OrderItem;
import pers.dc.ols.pojo.bo.center.OrderCommentsBO;
import pers.dc.ols.service.OrderService;
import pers.dc.ols.service.center.CenterCommentService;
import pers.dc.ols.utils.JSONResult;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "「用户中心-评论」相关接口")
@RequestMapping("mycomments")
@RestController
public class CenterCommentController {

    @Resource
    private CenterCommentService centerCommentService;

    @ApiOperation("获取待评价订单信息")
    @PostMapping("/pending")
    public JSONResult pending(String userId, String orderId) {
        List<OrderItem> orderItems = centerCommentService.queryPendingComments(orderId);
        if (orderItems == null)
            return JSONResult.errorMsg("订单无需评价");
        return JSONResult.ok(orderItems);
    }

    @ApiOperation("用户评价商品")
    @PostMapping("/saveList")
    public JSONResult saveList(String userId, String orderId, @RequestBody List<OrderCommentsBO> orderItemList) {
        if (orderItemList == null || orderItemList.size() == 0)
            return JSONResult.errorMsg("评论数据为空");

        centerCommentService.saveComments(userId, orderId, orderItemList);

        return JSONResult.ok();
    }
}
