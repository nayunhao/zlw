package com.zlead.good.controller;

import com.zlead.base.BaseController;
import com.zlead.domain.ApiResult;

import com.zlead.good.invoke.ZlwShopGoodsInvoke;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ZlwGoods")
@Slf4j
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class ZlwShopGoodsController extends BaseController {


    @Autowired
    private ZlwShopGoodsInvoke zlwShopGoodsInvoke;

    @PostMapping("/getShopClassLeve1")
    public ApiResult getShopClassLeve1(@RequestBody ApiResult apiResult){
        return zlwShopGoodsInvoke.getShopClassLeve1(apiResult);
    }

    @PostMapping("/getShopClassTree")
    public ApiResult getShopClassTree(@RequestBody ApiResult apiResult){
        return zlwShopGoodsInvoke.getShopClassTree(apiResult);
    }

}
