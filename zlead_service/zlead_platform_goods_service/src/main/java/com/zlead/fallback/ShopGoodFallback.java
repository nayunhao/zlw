package com.zlead.fallback;


import com.zlead.entity.goods.ZlwShopGoods;
import com.zlead.invoke.ShopGoodsInvoke;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShopGoodFallback implements ShopGoodsInvoke {

    @Override
    public List<ZlwShopGoods> selectSpuList(String pgId) {
        return null;
    }
}
