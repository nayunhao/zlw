package com.zlead.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.entity.goods.ZlwImportGoodsParam;
import com.zlead.entity.goods.ZlwPlatformGoodsBrand;
import com.zlead.entity.goods.ZlwPlatformGoodsVO;
import com.zlead.entity.goods.ZlwShopGoodsSpecsName;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
public interface IZlwShopGoodsSpecsNameService extends IService<ZlwShopGoodsSpecsName> {

    boolean insertShopGoodsSpecsName(Map map);
}
