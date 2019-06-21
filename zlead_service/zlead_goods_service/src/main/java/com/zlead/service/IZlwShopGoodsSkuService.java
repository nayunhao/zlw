package com.zlead.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.entity.goods.ZlwImportGoodsParam;
import com.zlead.entity.goods.ZlwPlatformGoodsVO;
import com.zlead.entity.goods.ZlwShopGoodsSku;

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
public interface IZlwShopGoodsSkuService extends IService<ZlwShopGoodsSku> {
    int batchEditGoodsStatus(String goodsId,String type);


    boolean batchSetGoodsClass(Map<String,Object> map);
    //List<ZlwShopGoodsSku> getGoodsListByStatus(ZlwShopGoodsSku zlwShopGoodsSku);

    boolean insertShopGoodsSku(Map map);

    Map<String,Page<ZlwShopGoodsSku>> getGoodsListByStatus(Map<String, Object> map);

//    List<ZlwShopGoodsSku> getGoodsListByStatus(ZlwShopGoodsSku zlwShopGoodsSku);
}
