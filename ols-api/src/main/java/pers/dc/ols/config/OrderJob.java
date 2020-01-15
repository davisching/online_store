package pers.dc.ols.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pers.dc.ols.pojo.OrderStatus;
import pers.dc.ols.service.OrderService;

import javax.annotation.Resource;

@Component
public class OrderJob {

    @Resource
    private OrderService orderService;

    @Scheduled(cron = "0 0/10 0/1 * * ?")
    public void autoCloseOrder() {
        orderService.closeAllUnpaidOrder();
    }
}