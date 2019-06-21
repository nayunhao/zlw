package com.zlead.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.entity.goods.ZlwPlatformGoodsImages;

import java.util.List;

public interface IZlwPlatformGoodsImagesService extends IService<ZlwPlatformGoodsImages> {

    public List<ZlwPlatformGoodsImages> getZlwPlatfomrGoodsImagesByPgId(Long pgId);
}
