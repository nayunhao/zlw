package com.zlead.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.entity.goods.ZlwPlatformGoodsSpecsName;
import com.zlead.entity.goods.ZlwPlatformGoodsSpecsNameVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
public interface IZlwPlatformGoodsSpecsNameService extends IService<ZlwPlatformGoodsSpecsName> {

   List<ZlwPlatformGoodsSpecsName> getZlwPlatformGoodsSpecsNameByPgsgId(String pgsgId);

    List<ZlwPlatformGoodsSpecsNameVO> getZlwPlatformGoodsSpecsNameVO(String pgsgId);
}
