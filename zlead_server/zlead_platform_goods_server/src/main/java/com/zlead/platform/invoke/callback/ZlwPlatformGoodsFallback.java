package com.zlead.platform.invoke.callback;

import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.ZlwPlatformGoodsClass;
import com.zlead.platform.invoke.ZlwPlatformGoodsInvoke;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Component
public class ZlwPlatformGoodsFallback implements ZlwPlatformGoodsInvoke {

    @Override
    public Map<String, List<ZlwPlatformGoodsClass>> getPlatFormClass(Map data) {
        return null;
    }

    @Override
    public Map<String, List<ZlwPlatformGoodsClass>> getAllPlatFormClass() {
        return null;
    }

    @Override
    public ApiResult getPlatformGoodsList(@RequestBody ApiResult apiResult){
        return null;
    }

}
