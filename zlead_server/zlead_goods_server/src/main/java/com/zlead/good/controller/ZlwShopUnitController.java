package com.zlead.good.controller;

import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.ZlwShopGoodsUnit;
import com.zlead.good.invoke.ZlwGoodsInvoke;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/ZlwGoods")
@Slf4j
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class ZlwShopUnitController {
    @Autowired
    private ZlwGoodsInvoke zlwGoodsInvoke;

    /* *
     * @Author xuguangwei
     * @Description //TODO 获取店铺所有商品单位
     * @Date 下午 3:25 2019/6/10 0005
     * @Param [apiRequest]
     * @return com.zlead.domain.ApiResult
     */
    @PostMapping("/getShopGoodsUnit")
    public ApiResult getShopGoodsUnit() {
        Map<String, List<ZlwShopGoodsUnit>> map =  zlwGoodsInvoke.getShopGoodsUnit();
        return ApiResult.isOkNoToken("请求成功", map);
    }
}
