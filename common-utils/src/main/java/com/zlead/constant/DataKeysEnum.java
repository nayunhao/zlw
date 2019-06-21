package com.zlead.constant;

public enum DataKeysEnum {

    P_USER_NAME("userName"),
    P_CODE("code"),
    P_AUTH_KEY_CODE("authCodeKey"),
    P_PASSWORD("password"),
    P_PHONE("phone"),
    P_PLATFORM("platform"),
    P_SHOPNAME("shopName"),
    P_USER_ID("userId"),
    P_COMPANY_ID("companyId")
    ;

    private String value;

    DataKeysEnum(String value) {
        this.value = value;
    }


    public String getValue(){
        return value;
    }

}
