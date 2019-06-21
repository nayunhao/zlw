package com.zlead.platform.controller;

import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.ZlwPlatformGoodsClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zlead.platform.invoke.ZlwPlatformGoodsInvoke;

import java.util.HashMap;
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
    private ZlwPlatformGoodsInvoke zlwPlatformGoodsInvoke;

    /* *
     * @Author xuguangwei
     * @Description //TODO 根据分类id查询下级分类,不传参数/0时,查询一级分类
     * @Date 下午 3:25 2019/6/10 0005
     * @Param [apiRequest]
     */
    @PostMapping("/getPlatFormClass")
    public ApiResult getPlatFormClass(@RequestBody ApiRequest apiRequest) {

        Map data = apiRequest.getData();
        String pgcId = (String) data.get("pgcId");
        Map param = new HashMap();
        param.put("pgcId", pgcId);

        Map<String, List<ZlwPlatformGoodsClass>> map =  zlwPlatformGoodsInvoke.getPlatFormClass(param);
        return ApiResult.isOkNoToken("请求成功", map);
    }

    /* *
     * @Author xuguangwei
     * @Description //TODO 查询所有平台分类
     * @Date 下午 3:25 2019/6/10 0005
     */
    @PostMapping("/getAllPlatFormClass")
    public ApiResult getAllPlatFormClass() {
        Map<String, List<ZlwPlatformGoodsClass>> map =  zlwPlatformGoodsInvoke.getAllPlatFormClass();
        return ApiResult.isOkNoToken("请求成功", map);
    }

}
