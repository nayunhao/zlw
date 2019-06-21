package com.zlead.platform.invoke.callback;

import com.zlead.entity.order.ZlwOrderCloseCause;
import com.zlead.platform.invoke.ZlwOrderInvoke;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ZlwOrderFallback implements ZlwOrderInvoke {

    @Override
    public List<ZlwOrderCloseCause> getUseableCloseOrderCause() {
        return null;
    }
}
