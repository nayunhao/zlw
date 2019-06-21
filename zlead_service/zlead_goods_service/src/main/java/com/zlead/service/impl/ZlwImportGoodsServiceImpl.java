package com.zlead.service.impl;

import com.zlead.entity.goods.ZlwImportGoodsParam;
import com.zlead.entity.goods.ZlwPlatformGoodsVO;
import com.zlead.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class ZlwImportGoodsServiceImpl implements IZlwImportGoodsService {

    @Autowired
    private IZlwShopGoodsService zlwShopGoodsService;
    @Autowired
    private IZlwShopGoodsSkuService zlwShopGoodsSkuService;
    @Autowired
    private IZlwShopGoodsImagesService zlwShopGoodsImagesService;
    @Autowired
    private IZlwShopGoodsPriceService zlwShopGoodsPriceService;
    @Autowired
    private IZlwShopGoodsInventoryService zlwShopGoodsInventoryService;
    @Autowired
    private IZlwShopGoodsSpecsGroupService zlwShopGoodsSpecsGroupService;
    @Autowired
    private IZlwShopGoodsSpecService zlwShopGoodsSpecService;

    @Override
    @Transactional(
            rollbackFor = {Exception.class}
    )
    public void importGoodsOne(ZlwPlatformGoodsVO zlwPlatformGoodsVO, ZlwImportGoodsParam zlwImportGoodsParam) throws Exception{
        Map map=new HashMap<String,Object>();
        map.put("goods",zlwPlatformGoodsVO);
        map.put("param",zlwImportGoodsParam);
        zlwShopGoodsService.insertShopGoods(map);
        zlwShopGoodsPriceService.insertShopGoodsPrice(map);
        zlwShopGoodsSpecsGroupService.insertShopGoodsSpecsGroup(map);
        zlwShopGoodsInventoryService.insertShopGoodsInventory(map);
        zlwShopGoodsSkuService.insertShopGoodsSku(map);
        zlwShopGoodsImagesService.insertShopGoodsImage(map);
    }
}
