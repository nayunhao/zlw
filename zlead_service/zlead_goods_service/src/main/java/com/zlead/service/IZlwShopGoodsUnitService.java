package com.zlead.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.ZlwShopGoodsUnit;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
public interface IZlwShopGoodsUnitService extends IService<ZlwShopGoodsUnit> {

    /**
     * 获取所有商品单位
     */
    Map<String, List<ZlwShopGoodsUnit>> getShopGoodsUnit();

}
