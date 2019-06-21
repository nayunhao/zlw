package com.zlead.platform.controller;

import com.zlead.domain.ApiResult;
import com.zlead.entity.order.ZlwOrderCloseCause;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zlead.platform.invoke.ZlwOrderInvoke;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/ZlwOrder")
@Slf4j
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class ZlwOrderController {
    @Autowired
    private ZlwOrderInvoke zlwOrderInvoke;


    /* *
     * @Author xuguangwei
     * @Description //TODO 获取可用的关闭比订单原因
     * @Date 下午 3:25 2019/6/21 0005
     * @Param [apiRequest]
     * @return com.zlead.domain.ApiResult
     */
    @PostMapping("/getCloseOrderCause")
    public ApiResult editGoodsStatus() {
        List<ZlwOrderCloseCause> list = zlwOrderInvoke.getUseableCloseOrderCause();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("causeList", list);

        return ApiResult.isOkNoToken("请求成功", resultMap);
    }

}
