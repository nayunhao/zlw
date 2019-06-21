package com.zlead.good.invoke;

import com.zlead.good.invoke.callback.CommonFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "zlead-common-service", fallback = CommonFallback.class)
public interface CommonInvoke {

    @PostMapping("/common/getGoodsId")
    public String getGoodsId();
}
