package com.zlead.constant;

public enum PlatformEnum {

    苹果("ios"),
    安卓("android"),
    网页("pc");

    private String value;

    PlatformEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
