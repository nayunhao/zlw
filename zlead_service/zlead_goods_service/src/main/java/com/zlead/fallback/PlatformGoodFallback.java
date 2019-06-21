package com.zlead.fallback;

import com.zlead.entity.goods.ZlwPlatformGoodsVO;
import com.zlead.invoke.PlatformGoodsInvoke;
import org.springframework.stereotype.Component;

@Component
public class PlatformGoodFallback implements PlatformGoodsInvoke {

    @Override
    public ZlwPlatformGoodsVO getPlatFormGoodsByPgId(String pgId) {
        System.out.println("平台导入报错了");
        return null;
    }
}
