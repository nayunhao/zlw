package com.zlead.controller;

import com.zlead.entity.goods.ZlwShopGoodsUnit;
import com.zlead.service.IZlwShopGoodsUnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/ZlwGoods")
@Slf4j
public class ZlwGoodsUnitController {
    @Autowired
    IZlwShopGoodsUnitService iZlwShopGoodsUnitService;

    /**
     * 获取所有商品单位
     */
    @PostMapping("/getShopGoodsUnit")
    public Map<String, List<ZlwShopGoodsUnit>> getShopGoodsUnit(){
        Map<String, List<ZlwShopGoodsUnit>> resultMap = null;
        try {
            resultMap = iZlwShopGoodsUnitService.getShopGoodsUnit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultMap;
    }

}
