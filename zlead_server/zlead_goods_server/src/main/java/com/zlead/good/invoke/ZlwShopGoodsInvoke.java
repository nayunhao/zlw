package com.zlead.good.invoke;


import com.zlead.domain.ApiResult;
import com.zlead.good.invoke.callback.ZlwShopGoodsFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@FeignClient(name = "zlead-goods-service", fallback = ZlwShopGoodsFallback.class)
public interface ZlwShopGoodsInvoke {

    @PostMapping("/goods/getShopClassLeve1")
    ApiResult getShopClassLeve1(@RequestBody ApiResult apiResult);

    @PostMapping("/goods/getShopClassTree")
    ApiResult getShopClassTree(@RequestBody ApiResult apiResult);
}
