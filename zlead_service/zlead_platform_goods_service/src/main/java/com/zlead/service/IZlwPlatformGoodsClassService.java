package com.zlead.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.entity.goods.ZlwPlatformGoodsClass;

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
public interface IZlwPlatformGoodsClassService extends IService<ZlwPlatformGoodsClass> {

    /**
     * 根据分类id查询下级分类
     * @param pgcId 不传参数/0时,查询一级分类
     */
    Map<String, List<ZlwPlatformGoodsClass>> getPlatFormClass(String pgcId);

    /**
     * 查询所有平台分类
     */
    Map<String, List<ZlwPlatformGoodsClass>> getAllPlatFormClass();

}
