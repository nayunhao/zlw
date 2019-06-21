package com.zlead.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.entity.goods.ZlwPlatformGoodsSpecsGroup;
import com.zlead.entity.goods.ZlwPlatformGoodsSpecsGroupVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
public interface IZlwPlatformGoodsSpecsGroupService extends IService<ZlwPlatformGoodsSpecsGroup> {

    List<ZlwPlatformGoodsSpecsGroupVO> getZlwPlatformGoodsSpecsGroupVOByPgId(Long pgId);
}
