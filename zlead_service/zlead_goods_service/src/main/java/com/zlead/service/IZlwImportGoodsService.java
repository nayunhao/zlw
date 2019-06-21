package com.zlead.service;

import com.zlead.entity.goods.ZlwImportGoodsParam;
import com.zlead.entity.goods.ZlwPlatformGoodsVO;

public interface IZlwImportGoodsService {
    void importGoodsOne(ZlwPlatformGoodsVO zlwPlatformGoodsVO, ZlwImportGoodsParam zlwImportGoodsParam) throws Exception;
}
