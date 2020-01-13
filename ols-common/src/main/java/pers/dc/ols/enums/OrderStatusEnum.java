package pers.dc.ols.enums;

public enum OrderStatusEnum {

    WAIT_PAY(10, "待付款"),
    WAIT_DELIVER(20, "待发货"),
    WAIT_RECEIVE(30, "已发货"),
    SUCCESS(40, "订单完成"),
    CLOSE(50, "订单关闭");

    public final Integer type;
    public final String value;

    OrderStatusEnum(Integer _type, String _value) {
        type = _type;
        value = _value;
    }
}
