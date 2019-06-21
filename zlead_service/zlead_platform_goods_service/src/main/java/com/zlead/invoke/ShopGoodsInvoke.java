package com.zlead.invoke;

import com.zlead.entity.goods.ZlwShopGoods;
import com.zlead.fallback.ShopGoodFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "zlead-goods-service", fallback = ShopGoodFallback.class)
public interface ShopGoodsInvoke {

    @PostMapping("goods/ShopGoods/{shopId}")
    List<ZlwShopGoods> selectSpuList(@PathVariable("shopId") String shopId);
}
