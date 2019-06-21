package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwShopGoodsSpecMapper;
import com.zlead.entity.goods.ZlwImportGoodsParam;
import com.zlead.entity.goods.ZlwPlatformGoodsVO;
import com.zlead.entity.goods.ZlwShopGoodsSpec;
import com.zlead.service.IZlwShopGoodsSpecService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
@Service
public class ZlwShopGoodsSpecServiceImpl extends ServiceImpl<ZlwShopGoodsSpecMapper, ZlwShopGoodsSpec> implements IZlwShopGoodsSpecService {

    @Override
    public boolean insertShopGoodsSpec(Map map) {
        ZlwImportGoodsParam zlwImportGoodsParam=(ZlwImportGoodsParam)map.get("param");
        ZlwShopGoodsSpec zlwShopGoodsSpec=new ZlwShopGoodsSpec();
        return false;
    }
}
