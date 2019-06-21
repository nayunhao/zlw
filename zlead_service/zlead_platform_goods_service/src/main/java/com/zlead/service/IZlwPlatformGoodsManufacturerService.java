package com.zlead.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.entity.goods.ZlwPlatformGoodsManufacturer;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
public interface IZlwPlatformGoodsManufacturerService extends IService<ZlwPlatformGoodsManufacturer> {

    public ZlwPlatformGoodsManufacturer getZlwPlatformGoodsManufacturerByPgId(Long pgmId);
}
