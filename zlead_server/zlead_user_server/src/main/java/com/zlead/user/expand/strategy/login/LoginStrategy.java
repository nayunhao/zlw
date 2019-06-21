package com.zlead.user.expand.strategy.login;

import com.zlead.domain.ApiResult;

import java.util.Map;

public interface LoginStrategy {

    public static ApiResult apiResult= new ApiResult();

    public ApiResult login(Map loginInfo);
}
