package com.zlead.good.invoke.callback;

import com.zlead.domain.ApiResult;

import com.zlead.good.invoke.ZlwShopGoodsInvoke;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @ClassName 刘祎航
 * @Description TODO
 * @Author Administrator
 * @Date 2019/6/1115:04
 * @Version 1.0
 **/
@Component
public class ZlwShopGoodsFallback implements ZlwShopGoodsInvoke {

    @Override
    public ApiResult getShopClassLeve1(@RequestBody ApiResult apiResult){
        return null;
    }

    @Override
    public ApiResult getShopClassTree(@RequestBody ApiResult apiResult){
        return null;
    }
}
