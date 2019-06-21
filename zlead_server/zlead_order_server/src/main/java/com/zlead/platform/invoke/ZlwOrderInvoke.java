package com.zlead.platform.invoke;

import com.zlead.entity.order.ZlwOrderCloseCause;
import org.springframework.cloud.openfeign.FeignClient;
import com.zlead.platform.invoke.callback.ZlwOrderFallback;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "zlead-order-service", fallback = ZlwOrderFallback.class)
public interface ZlwOrderInvoke {

    @PostMapping("/ZlwOrder/getUseableCloseOrderCause")
    List<ZlwOrderCloseCause> getUseableCloseOrderCause();

}
