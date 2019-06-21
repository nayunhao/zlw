package com.zlead.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.entity.goods.ZlwPlatformGoodsSpecs;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
public interface IZlwPlatformGoodsSpecsService extends IService<ZlwPlatformGoodsSpecs> {

    public List<ZlwPlatformGoodsSpecs> getZlwPlatformGoodsSpecsByPgId(Long pgId);
}
