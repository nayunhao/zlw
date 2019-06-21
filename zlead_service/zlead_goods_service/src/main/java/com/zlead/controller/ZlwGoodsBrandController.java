package com.zlead.controller;

import com.zlead.service.IZlwShopGoodsBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/ZlwGoods")
@Slf4j
public class ZlwGoodsBrandController {
    @Autowired
    IZlwShopGoodsBrandService iZlwShopGoodsBrandService;

    /**
     * 获取所有品牌
     */
    @PostMapping("/getGoodsBrand")
    public Map<String, Object> getGoodsBrand(@RequestBody Map<String, Object> data){
        Map<String, Object> resultMap = null;
        try {
            String searchKey = (String) data.get("searchKey");
            resultMap = iZlwShopGoodsBrandService.getShopGoodsBrand(searchKey);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultMap;
    }

}
