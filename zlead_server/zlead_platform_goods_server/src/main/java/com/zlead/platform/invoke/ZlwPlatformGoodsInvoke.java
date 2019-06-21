package com.zlead.platform.invoke;

import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.ZlwPlatformGoodsClass;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.zlead.platform.invoke.callback.ZlwPlatformGoodsFallback;

import java.util.List;
import java.util.Map;

@FeignClient(name = "zlead-platform-goods-service", fallback = ZlwPlatformGoodsFallback.class)
public interface ZlwPlatformGoodsInvoke {

    @PostMapping("/ZlwPlatformGoods/getPlatFormClass")
    Map<String, List<ZlwPlatformGoodsClass>> getPlatFormClass(@RequestBody Map data);

    @PostMapping("/ZlwPlatformGoods/getAllPlatFormClass")
    Map<String, List<ZlwPlatformGoodsClass>> getAllPlatFormClass();

    @PostMapping("/ZlwGoods/getPlatformGoodsList")
    ApiResult getPlatformGoodsList(@RequestBody ApiResult apiResult);
}
