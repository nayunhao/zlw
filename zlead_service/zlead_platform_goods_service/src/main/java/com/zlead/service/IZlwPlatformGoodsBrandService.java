package com.zlead.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.entity.goods.ZlwPlatformGoodsBrand;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
public interface IZlwPlatformGoodsBrandService extends IService<ZlwPlatformGoodsBrand> {

    public ZlwPlatformGoodsBrand getZlwPlatformGoodsBrandByPgId(Long pgbId);
}
