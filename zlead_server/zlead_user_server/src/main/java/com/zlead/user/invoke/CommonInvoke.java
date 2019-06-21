package com.zlead.user.invoke;

import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.domain.VerifyCodeEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.zlead.user.invoke.callback.CommonFallback;

@FeignClient(name = "zlead-common-service", fallback = CommonFallback.class)
public interface CommonInvoke {

    @PostMapping("common/checkCode")
    public ApiResult checkCode(@RequestBody VerifyCodeEntity verifyCodeEntity);

    @PostMapping("/common/checkSmsCode")
    public ApiResult checkSmsCode(@RequestBody ApiRequest apiRequest);

    @PostMapping("/common/getUserId")
    public String getUserId();

    @PostMapping("/common/getOrderId")
    public String getOrderId();

    @PostMapping("/common/getGoodsId")
    public String getGoodsId();

    @PostMapping("/common/getCompanyId")
    public String getCompanyId();
}
