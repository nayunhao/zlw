package com.zlead.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.zlead.base.BaseController;
import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.constant.ServiceReturnCode;
import com.zlead.entity.goods.ZlwImportGoodsParam;
import com.zlead.entity.goods.ZlwPlatformGoodsVO;
import com.zlead.invoke.PlatformGoodsInvoke;
import com.zlead.service.IZlwImportGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/ZlwGoods")
@Slf4j
public class ZlwImportGoodsController extends BaseController {

    @Autowired
    private PlatformGoodsInvoke platformGoodsInvoke;
    @Autowired
    private IZlwImportGoodsService zlwImportGoodsService;

    Logger logger= LoggerFactory.getLogger(ZlwImportGoodsController.class);
    @PostMapping("/importGoods")
    public ApiResult importGoods(@RequestBody ApiRequest apiRequest) {
        log.info("入参：{}",new Gson().toJson(apiRequest));
        Map data = apiRequest.getData();
        List<ZlwImportGoodsParam> list = null;
        if (data != null) {
            ObjectMapper mapper = new ObjectMapper();
            list = mapper.convertValue(data.get("goods"), new TypeReference<List<ZlwImportGoodsParam>>() {
            });
        } else {
            return ApiResult.isErrMessage(ServiceReturnCode.IMPORT_GOODS_ISEMPTY);
        }
        AtomicInteger succ = new AtomicInteger(0);
        AtomicInteger err = new AtomicInteger(0);
        if (!CollectionUtils.isEmpty(list)) {
            list.parallelStream().forEach(e -> {
                try {
                    logger.info(e.getPgId());
                    if(!StringUtils.isEmpty(e.getPgId())) {
                        ZlwPlatformGoodsVO zlwPlatformGoodsVO = platformGoodsInvoke.getPlatFormGoodsByPgId(e.getPgId());
                        if (zlwPlatformGoodsVO != null) {
                            zlwImportGoodsService.importGoodsOne(zlwPlatformGoodsVO, e);
                            succ.incrementAndGet();
                        }
                    }else{
                        throw new RuntimeException(ServiceReturnCode.INPUT_PLATFORM_PGID.getMessage());
                    }
                } catch (Exception x) {
                    err.incrementAndGet();
                    logger.info(x.getMessage());
                    logger.info("从平台导入到店铺商品表有错误");
                    x.printStackTrace();
                }
            });
        }
        Map result = new HashMap<String, Object>();
        int succInt = succ.get();
        int errInt = err.get();
        if (succInt > 0) {
            result.put("isSuccess", Boolean.TRUE.toString());
        } else {
            result.put("isSuccess", Boolean.FALSE.toString());
        }
        result.put("succNum", String.valueOf(succInt));
        result.put("errNum", String.valueOf(errInt));
        return ApiResult.isOkNoToken(String.format("保存成功【%d】条,失败【%d】条", succInt, errInt), result);
    }
}

