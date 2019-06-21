package com.zlead.constant;

/**
 * Created by Ricky on 2019/6/13
 */


public enum ShopsEnum {

    S_GOODS_ENUM("goods");

    private String value;

    ShopsEnum(String value) {
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
