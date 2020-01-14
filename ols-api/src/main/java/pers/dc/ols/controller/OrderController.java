package pers.dc.ols.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pers.dc.ols.enums.OrderStatusEnum;
import pers.dc.ols.enums.PaymentMethod;
import pers.dc.ols.pojo.bo.OrderCreateBO;
import pers.dc.ols.pojo.vo.MerchantOrdersVO;
import pers.dc.ols.pojo.vo.OrderVO;
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

    final String PAYMENT_URL = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";

    @Resource
    private OrderService orderService;

    @Resource
    private RestTemplate restTemplate;

    @ApiOperation("创建订单")
    @PostMapping("/create")
    public JSONResult create(@RequestBody OrderCreateBO orderCreateBO,
                             HttpServletRequest request,
                             HttpServletResponse response) {

        if (!orderCreateBO.getPayMethod().equals(PaymentMethod.WEIXIN.type)
                && !orderCreateBO.getPayMethod().equals(PaymentMethod.ALIPAY.type))
            return JSONResult.errorMsg("支付方式不支持");

        OrderVO orderVO = orderService.createOrder(orderCreateBO);

        // 2。 移除购物车中的内容
        // TODO 整合 redis， 移除购物车中的内容
        CookieUtils.setCookie(request, response, "shopcart", "", true);

        // 3。 向支付中心发送信息
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("imoocUserId", "8377087-1454853538");
        headers.add("password", "poek-0reo-r0oi-3krk");
        MerchantOrdersVO merchantOrdersVO = orderVO.getMerchantOrdersVO();
        merchantOrdersVO.setAmount(1);
        HttpEntity<MerchantOrdersVO> entity = new HttpEntity<>(merchantOrdersVO, headers);
        ResponseEntity<JSONResult> responseEntity = restTemplate.postForEntity(PAYMENT_URL, entity, JSONResult.class);
        JSONResult paymentResponse = responseEntity.getBody();

        if (paymentResponse == null || !paymentResponse.isOK())
            return JSONResult.errorMsg("支付失败，请联系管理员！");

        return JSONResult.ok(orderVO.getOrderId());
    }

    @ApiOperation("订单支付完成，更新状态")
    @PostMapping("/notifyOrderPaid")
    public Integer notifyOrderPaid(String merchantOrderId) {
        orderService.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_DELIVER.type);
        return HttpStatus.OK.value();
    }

    @ApiOperation("获取订单状态")
    @PostMapping("/getPaidOrderInfo")
    public JSONResult getPaidOrderInfo(String orderId) {
        return JSONResult.ok(orderService.queryOrderStatusByOrderId(orderId));
    }
}