package com.zlead.platform.controller;
import com.zlead.base.BaseController;
import com.zlead.domain.ApiResult;

import com.zlead.platform.invoke.ZlwPlatformGoodsInvoke;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/ZlwPlatformGoods")
@Slf4j
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class ZlwPlatformGoodsController extends BaseController {


    @Autowired
    private ZlwPlatformGoodsInvoke zlwPlatformGoodsInvoke;

    @PostMapping("/getPlatformGoodsList")
    public ApiResult getPlatformGoodsList(@RequestBody ApiResult apiResult){
        ApiResult platformGoodsList = zlwPlatformGoodsInvoke.getPlatformGoodsList(apiResult);
        return  platformGoodsList;
    }

//    @PostMapping("/getPlatformGoodsList")
//    public ResponseEntity clientMsg(@RequestBody ApiResult apiResult){
//        Map<String,Object> map= new HashMap<>();
//        map.put("ApiResult",apiResult);
//        //第一种方式
//        RestTemplate restTemplate = new RestTemplate();
//        //ZlwPlatformGoods response = restTemplate.getForObject("http://localhost:8100/ZlwGoods/getPlatformGoodsList",ZlwPlatformGoods.class);
//        ResponseEntity<ApiResult> apiResultResponseEntity = restTemplate.postForEntity("http://localhost:8100/ZlwGoods/getPlatformGoodsList", apiResult, ApiResult.class, map);
//        return apiResultResponseEntity;
//    }

}
