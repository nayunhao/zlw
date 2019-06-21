package com.zlead.platform.invoke.callback;

import com.zlead.entity.pay.ZlwPayChanel;
import com.zlead.platform.invoke.ZlwPayInvoke;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ZlwPayFallback implements ZlwPayInvoke {

    @Override
    public List<ZlwPayChanel> getPayChanel() {
        return null;
    }
}
