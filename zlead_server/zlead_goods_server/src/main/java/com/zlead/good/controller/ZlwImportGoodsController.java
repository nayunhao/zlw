package com.zlead.good.controller;

import com.zlead.base.BaseController;
import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.good.invoke.ZlwGoodsInvoke;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ZlwGoods")
@Slf4j
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class ZlwImportGoodsController extends BaseController {

    @Autowired
    private ZlwGoodsInvoke zlwGoodsInvoke;
    @PostMapping("/importGoods")
    public ApiResult importGoods(@RequestBody ApiRequest apiRequest){
        return zlwGoodsInvoke.importGoods(apiRequest);
    }
}
