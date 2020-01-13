package pers.dc.ols.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.dc.ols.enums.PaymentMethod;
import pers.dc.ols.pojo.bo.OrderCreateBO;
import pers.dc.ols.service.OrderService;
import pers.dc.ols.utils.CookieUtils;
import pers.dc.ols.utils.JSONResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "「订单」相关接口")
@RequestMapping("orders")
@RestController
public class OrderController {

    @Resource
    OrderService orderService;

    @ApiOperation("创建订单")
    @PostMapping("create")
    public JSONResult create(@RequestBody OrderCreateBO orderCreateBO,
                             HttpServletRequest request,
                             HttpServletResponse response) {

        if (orderCreateBO.getPayMethod() != PaymentMethod.WEIXIN.type
                && orderCreateBO.getPayMethod() != PaymentMethod.ALIPAY.type)
            return JSONResult.errorMsg("支付方式不支持");

        String orderId = orderService.createOrder(orderCreateBO);

        // 2。 移除购物车中的内容
        // TODO 整合 redis， 移除购物车中的内容
        CookieUtils.setCookie(request, response, "shopcart", "", true);

        // 3。 向支付中心发送信息
        System.out.println(orderId);

        return JSONResult.ok();
    }
}