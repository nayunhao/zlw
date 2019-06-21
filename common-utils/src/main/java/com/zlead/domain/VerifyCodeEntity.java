package com.zlead.domain;

public class VerifyCodeEntity {

    private String authCodeKey;
    private String verifyCode;

    public String getAuthCodeKey() {
        return authCodeKey;
    }

    public void setAuthCodeKey(String authCodeKey) {
        this.authCodeKey = authCodeKey;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
