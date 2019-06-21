package com.zlead.controller;

import com.zlead.entity.goods.ZlwPlatformGoodsClass;
import com.zlead.service.IZlwPlatformGoodsClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/ZlwPlatformGoods")
@Slf4j
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class ZlwPlatformClassController {
    @Autowired
    IZlwPlatformGoodsClassService iZlwPlatformGoodsClassService;

    /**
     * 根据分类id查询下级分类
     * pgcId  不传参数/0时,查询一级分类
     */
    @PostMapping("/getPlatFormClass")
    public Map<String, List<ZlwPlatformGoodsClass>> getPlatFormClass(@RequestBody Map data){
        String pgcId = (String) data.get("pgcId");

        Map<String, List<ZlwPlatformGoodsClass>> result = null;
        try {
            result = iZlwPlatformGoodsClassService.getPlatFormClass(pgcId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询所有平台分类
     */
    @PostMapping("/getAllPlatFormClass")
    public Map<String, List<ZlwPlatformGoodsClass>> getAllPlatFormClass(){

        Map<String, List<ZlwPlatformGoodsClass>> result = null;
        try {
            result = iZlwPlatformGoodsClassService.getAllPlatFormClass();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
