package pers.dc.ols.enums;

public enum PaymentMethod {

    WEIXIN(1, "微信"),
    ALIPAY(2, "支付宝");

    public final Integer type;
    public final String value;

    PaymentMethod(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
