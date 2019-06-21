package com.zlead.platform.invoke;

import com.zlead.entity.pay.ZlwPayChanel;
import org.springframework.cloud.openfeign.FeignClient;
import com.zlead.platform.invoke.callback.ZlwPayFallback;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "zlead-pay-service", fallback = ZlwPayFallback.class)
public interface ZlwPayInvoke {

    @PostMapping("/ZlwPay/getPayChanel")
    List<ZlwPayChanel> getPayChanel();

}
