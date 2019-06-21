package com.zlead.good.invoke.callback;

import com.zlead.good.invoke.CommonInvoke;
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
