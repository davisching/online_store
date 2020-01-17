package pers.dc.ols.controller.center;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.dc.ols.enums.OrderStatusEnum;
import pers.dc.ols.service.OrderService;
import pers.dc.ols.service.center.MyOrderService;
import pers.dc.ols.utils.JSONResult;

import javax.annotation.Resource;

@Api(tags = "「用户中心-订单」相关接口")
@RequestMapping("myorders")
@RestController
public class CenterOrderController {

    @Resource
    private MyOrderService myOrderService;
    @Resource
    private OrderService orderService;

    @ApiOperation("查询个人订单信息")
    @PostMapping("/query")
    public JSONResult query(String userId, Integer orderStatus, Integer page, Integer pageSize) {
        return JSONResult.ok(myOrderService.queryMyOrders(userId, orderStatus, page, pageSize));
    }

    @ApiOperation("商家发货")
    @PostMapping("/deliver")
    public JSONResult deliver(String orderId) {
        if (StringUtils.isBlank(orderId))
            return JSONResult.errorMsg("订单号不能为空");
        orderService.updateOrderStatus(orderId, OrderStatusEnum.WAIT_RECEIVE.type);
        return JSONResult.ok();
    }

    @ApiOperation("用户确认收货")
    @PostMapping("/confirmReceive")
    public JSONResult confirmReceive(String userId, String orderId) {
       if (!myOrderService.userHasOrder(userId, orderId))
           return JSONResult.errorMsg("用户不存在此订单！");
       orderService.updateOrderStatus(orderId, OrderStatusEnum.SUCCESS.type);
       return JSONResult.ok();
    }

    @ApiOperation("删除已关闭订单")
    @PostMapping("/delete")
    public JSONResult delete(String userId, String orderId) {
        if (!myOrderService.userHasOrder(userId, orderId))
            return JSONResult.errorMsg("用户不存在此订单！");
        orderService.deleteOrder(orderId);
        return JSONResult.ok();
    }

    @ApiOperation("用户中心-显示各状态订单数量")
    @PostMapping("/statusCounts")
    public JSONResult userId(String userId) {
        return JSONResult.ok(myOrderService.queryOrderStatusCounts(userId));
    }
}