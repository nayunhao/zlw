package com.zlead.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.entity.goods.ZlwPlatformGoodsSpecsValue;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
public interface IZlwPlatformGoodsSpecsValueService extends IService<ZlwPlatformGoodsSpecsValue> {

     List<ZlwPlatformGoodsSpecsValue> getZlwPlatformGoodsSpecsValueByPgsnId(String pgId);
}
