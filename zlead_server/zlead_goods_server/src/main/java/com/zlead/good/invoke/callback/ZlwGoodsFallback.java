package com.zlead.good.invoke.callback;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.*;

import com.zlead.good.invoke.ZlwGoodsInvoke;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ZlwGoodsFallback implements ZlwGoodsInvoke {


    @Override
    public boolean addShopGoodsClass(ZlwShopGoodsClass zlwShopGoodsClass) {
        return false;
    }

    @Override
    public ApiResult importGoods(ApiRequest apiRequest) {
        return null;
    }

    @Override
    public Map<String, Page<ZlwShopGoodsSku>> getGoodsListByStatus(Map data) {
        ApiResult err =  ApiResult.isErrMessage("");
        return null;
    }



    @Override
    public boolean setBatchGoodsClass(Map map) {
        ApiResult err =  ApiResult.isErrMessage("");
        return false;
    }

    @Override
    public int editShopGoodsClass(Map data) {
        return 1;
    }

    @Override
    public boolean removeShopGoodsClass(String sgcId) {
        return false;
    }

    @Override
    public int editGoodsStatus(Map map) {
        ApiResult err =  ApiResult.isErrMessage("");
        return 4;
    }

    /**
     * nayunhao
     *
     */
    @Override
    public boolean addShopClass(ZlwShopGoodsClass zlwShopGoodsClass) {
        return false;
    }
    @Override
    public ZlwShopGoodsClass getClassByName(String className) {
        return null;
    }

    @Override
    public boolean addGoodsByOne(Map<String, Object> data) {
        return false;
    }


    @Override
    public Map<String, List<ZlwShopGoodsUnit>> getShopGoodsUnit() {
        return null;
    }

    @Override
    public Map<String, Page<ZlwShopGoods>> getGoodsSpuListByStatus(Map data) {
        ApiResult err =  ApiResult.isErrMessage("");
        return null;
    }

    @Override
    public Map<String, Object> getGoodsBrand(Map<String, Object> data) {
        return null;
    }

    @Override
    public ApiResult checkCustomInputCode(Map map) {
        return null;
    }

}
