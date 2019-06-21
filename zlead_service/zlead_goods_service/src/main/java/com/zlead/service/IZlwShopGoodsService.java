package com.zlead.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.*;
import com.zlead.exception.SystemException;

import java.util.List;
import java.util.Map;



import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
public interface IZlwShopGoodsService extends IService<ZlwShopGoods> {

    /**
     * 校验自定义商品编码
     * @param shopId 店铺id
     * @param customCode 商品编码
     * @return
     */
    ApiResult checkCustomInputCode(String shopId, String customCode);
    /**
     * 添加商品，需要调用事务
     * @param zlwShopGoods 商品店铺spu表
     * @param zlwShopGoodsSku  商品店铺sku表
     * @param zlwShopGoodsSpec  店铺SKU-商品规格表
     * @param zlwShopGoodsPrice 店铺SKU-商品价格表
     * @param zlwShopGoodsSpecsName 店铺SKU-商品名称规格表
     * @param zlwShopGoodsSpecsValue 店铺SKU-商品规格值表
     * @param ZlwShopGoodsImages 店铺SKU-商品图片表
     * @param zlwShopGoodsInventory 店铺SKU-商品库存表
     */
    void addGoodsTrans(ZlwShopGoods zlwShopGoods,
                       ZlwShopGoodsSku zlwShopGoodsSku,
                       ZlwShopGoodsSpec zlwShopGoodsSpec,
                       ZlwShopGoodsPrice zlwShopGoodsPrice,
                       ZlwShopGoodsSpecsName zlwShopGoodsSpecsName,
                       ZlwShopGoodsSpecsValue zlwShopGoodsSpecsValue,
                       ZlwShopGoodsImages ZlwShopGoodsImages,
                       ZlwShopGoodsInventory zlwShopGoodsInventory) throws Exception;

    boolean insertShopGoods(Map map);

    Map<String,Page<ZlwShopGoods>> getGoodsSpuListByStatus(Map<String, Object> map);

    List<ZlwShopGoods> selectSpuList(String shopId,Integer sgIsDelete);
}
