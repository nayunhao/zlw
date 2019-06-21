package com.zlead.invoke;

import com.zlead.domain.ApiRequest;
import com.zlead.entity.goods.ZlwPlatformGoodsVO;
import com.zlead.fallback.PlatformGoodFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "zlead-platform-goods-service", fallback = PlatformGoodFallback.class)
public interface PlatformGoodsInvoke {

    @PostMapping("/ZlwPlatformGoods/platformGoods/{pgId}")
    ZlwPlatformGoodsVO getPlatFormGoodsByPgId(@PathVariable("pgId") String pgId);
}
