package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.zlead.dao.mapper.ZlwShopMapper;
import com.zlead.entity.shop.ZlwShop;
import com.zlead.service.ZlwShopService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZlwShopServiceImpl extends ServiceImpl<ZlwShopMapper, ZlwShop> implements ZlwShopService {


    @Override
    public boolean addShop(ZlwShop zlwShop) {

        int res = this.baseMapper.insert(zlwShop);

        return res>0 ? true : false;
    }

    @Override
    public boolean getShopByInfo(ZlwShop zlwShop) {
        Map<String,Object> map = new HashMap<>();
        map.put("shop_name",zlwShop.getShopName());
        List<ZlwShop> shopList = this.baseMapper.selectByMap(map);

        if(shopList.size()>0){
            return false;
        }
        return true;
    }

}
