package com.zlead.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlead.entity.goods.ZlwPlatformGoods;
import com.zlead.entity.goods.ZlwPlatformGoodsVO;

public interface IZlwImportGoodsService {

   ZlwPlatformGoodsVO getZlwPlatformGoodsVOByPgId(String pgId);

}
