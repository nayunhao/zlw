package com.zlead.constant;

public enum LoginMessageEnum {

    L_NOT_REG("当前用户没有注册信息", 300),
    L_ALREADY_REG("当前用户已经注册",301),
    L_ADDUSER_OK("添加用户成功",200),
    L_ADDUSER_ERR("添加用户失败",302),
    L_ADDSHOP_OK("添加店铺成功",200),
    L_ADDSHOP_ERR("添加店铺失败",303),
    L_ADDCOMPANY_OK("添加公司成功",200),
    L_ADDCOMPANY_ERR("添加公司失败",304),
    L_NOSHOP_INFO("没有查询到店铺信息",305),
    L_SHOP_INFO("成功查询到店铺信息",200),
    L_CHANGE_PASS_OK("修改密码成功",200),
    L_CHANGE_PASS_ERR("修改密码失败",306),
    L_SHOP_ALREADY_REG("当前店铺名已经被注册",307),
    L_SHOP_NOTYET_REG("当前店铺名称还未注册",200),
    L_PASSWORD_ERR("密码错误",308),
    L_LOGIN_OK("登录成功",200)
    ;

    private String message;
    private int code;

    LoginMessageEnum(String message, int code) {
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
