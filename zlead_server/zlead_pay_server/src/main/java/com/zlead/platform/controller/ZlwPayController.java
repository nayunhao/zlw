package com.zlead.platform.controller;

import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.ZlwShopGoodsUnit;
import com.zlead.entity.pay.ZlwPayChanel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zlead.platform.invoke.ZlwPayInvoke;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/ZlwPay")
@Slf4j
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class ZlwPayController {
    @Autowired
    private ZlwPayInvoke zlwPayInvoke;

    /* *
     * @Author xuguangwei
     * @Description //TODO  查询支付通道
     * @Date 下午 3:25 2019/6/10 0005
     * @Param [apiRequest]
     * @return com.zlead.domain.ApiResult
     */
    @PostMapping("/getPayChanel")
    public ApiResult getPayChanel() {
        List<ZlwPayChanel> list = zlwPayInvoke.getPayChanel();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("payChanelList", list);

        return ApiResult.isOkNoToken("请求成功", resultMap);
    }

}
