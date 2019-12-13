package pers.dc.ols.enums;

public enum Gender {

    FEMALE(0, "女"),
    MALE(1, "男"),
    CONFIDENTIAL(2, "保密");

    public final Integer type;
    public final String value;

    Gender(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
