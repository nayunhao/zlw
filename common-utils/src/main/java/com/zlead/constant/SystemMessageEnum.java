package com.zlead.constant;

public enum SystemMessageEnum {

    TOKEN_INVALID("Token校验失败",600);

    private String message;
    private int code;

     SystemMessageEnum(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
