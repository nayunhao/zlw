package com.zlead.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.entity.shop.ZlwShop;
import com.zlead.entity.user.ZlwUser;

import java.util.List;

public interface ZlwUserService extends IService<ZlwUser> {

    public ZlwUser getUserByName(String userName);

    public boolean addAUser(ZlwUser zlwUser);

    public boolean updateUserInfo(ZlwUser zlwUser);

    public ZlwUser getShopListByUser(String userId);

}
