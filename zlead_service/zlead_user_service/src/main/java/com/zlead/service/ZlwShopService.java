package com.zlead.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.entity.shop.ZlwShop;

public interface ZlwShopService extends IService<ZlwShop> {

    public boolean addShop(ZlwShop zlwShop);

    public boolean getShopByInfo(ZlwShop zlwShop);
}
