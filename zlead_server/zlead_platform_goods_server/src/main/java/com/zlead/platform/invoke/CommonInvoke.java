package com.zlead.platform.invoke;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import com.zlead.platform.invoke.callback.CommonFallback;

@FeignClient(name = "zlead-common-service", fallback = CommonFallback.class)
public interface CommonInvoke {

    @PostMapping("/common/getGoodsId")
    public String getGoodsId();
}
