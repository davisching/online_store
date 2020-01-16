package pers.dc.ols.controller.center;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.dc.ols.service.center.MyOrderService;
import pers.dc.ols.utils.JSONResult;

import javax.annotation.Resource;

@Api(tags = "「用户中心-订单」相关接口")
@RequestMapping("myorders")
@RestController
public class CenterOrderController {

    @Resource
    private MyOrderService myOrderService;

    @ApiOperation("查询个人订单信息")
    @PostMapping("/query")
    public JSONResult query(String userId, Integer orderStatus, Integer page, Integer pageSize) {
        return JSONResult.ok(myOrderService.queryMyOrders(userId, orderStatus, page, pageSize));
    }
}
