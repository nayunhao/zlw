package com.zlead.user.invoke.callback;

import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.domain.VerifyCodeEntity;
import com.zlead.user.invoke.CommonInvoke;
import org.springframework.stereotype.Component;

@Component
public class CommonFallback implements CommonInvoke {

    @Override
    public ApiResult checkCode(VerifyCodeEntity verifyCodeEntity) {

        return ApiResult.isErrMessage("验证失败");

    }

    @Override
    public ApiResult checkSmsCode(ApiRequest apiRequest) {
        return ApiResult.isErrMessage("验证失败");
    }

    @Override
    public String getUserId() {
        return null;
    }

    @Override
    public String getOrderId() {
        return null;
    }

    @Override
    public String getGoodsId() {
        return null;
    }

    @Override
    public String getCompanyId() {
        return null;
    }
}
