package com.zlead.controller;

import com.zlead.entity.goods.ZlwPlatformGoodsVO;
import com.zlead.service.IZlwImportGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class ZlwImportGoodsController {

    @Autowired
    private IZlwImportGoodsService zlwImportGoodsService;
    @PostMapping("/ZlwPlatformGoods/platformGoods/{pgId}")
    public ZlwPlatformGoodsVO getPlatFormGoodsByPgId(@PathVariable("pgId") String pgId){
        return zlwImportGoodsService.getZlwPlatformGoodsVOByPgId(pgId);
    }
}
