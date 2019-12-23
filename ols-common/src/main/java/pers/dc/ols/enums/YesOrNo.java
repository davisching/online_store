package pers.dc.ols.enums;

public enum YesOrNo {

    No(0),
    Yes(1);

    public int getType() {
        return type;
    }

    public final int type;

    YesOrNo(int type) {
        this.type = type;
    }
}
