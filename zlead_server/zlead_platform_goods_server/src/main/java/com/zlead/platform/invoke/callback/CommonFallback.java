package com.zlead.platform.invoke.callback;

import com.zlead.platform.invoke.CommonInvoke;
import org.springframework.stereotype.Component;

@Component
public class CommonFallback implements CommonInvoke {

    /**
     * 熔断机制，如果出现问题，返回00000
     * @return
     */
    @Override
    public String getGoodsId() {
        return "00000";
    }
}
