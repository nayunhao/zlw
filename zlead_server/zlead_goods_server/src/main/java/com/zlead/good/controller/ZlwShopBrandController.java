package com.zlead.good.controller;

import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.good.invoke.ZlwGoodsInvoke;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/ZlwGoods")
@Slf4j
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class ZlwShopBrandController {
    @Autowired
    private ZlwGoodsInvoke zlwGoodsInvoke;

    /* *
     * @Author xuguangwei
     * @Description //TODO 获取店铺所有品牌
     * @Date 下午 3:25 2019/6/10 0005
     * @Param [apiRequest]
     * @return com.zlead.domain.ApiResult
     */
    @PostMapping("/getGoodsBrand")
    public ApiResult getGoodsBrand(@RequestBody ApiRequest apiRequest) {

        Map data = apiRequest.getData();
        String searchKey = (String) data.get("searchKey");

        Map<String, Object> param = new HashMap();
        param.put("searchKey", searchKey);

        Map<String, Object> map =  zlwGoodsInvoke.getGoodsBrand(param);
        return ApiResult.isOkNoToken("请求成功", map);
    }
}
