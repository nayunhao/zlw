package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwShopGoodsInventoryMapper;
import com.zlead.entity.goods.ZlwImportGoodsParam;
import com.zlead.entity.goods.ZlwPlatformGoodsVO;
import com.zlead.entity.goods.ZlwShopGoodsInventory;
import com.zlead.service.IZlwShopGoodsInventoryService;
import com.zlead.utils.UUIDUtils;
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
public class ZlwShopGoodsInventoryServiceImpl extends ServiceImpl<ZlwShopGoodsInventoryMapper, ZlwShopGoodsInventory> implements IZlwShopGoodsInventoryService {

    @Override
    public boolean insertShopGoodsInventory(Map map) {
        ZlwImportGoodsParam zlwImportGoodsParam=(ZlwImportGoodsParam)map.get("param");
        ZlwShopGoodsInventory zlwShopGoodsInventory=new ZlwShopGoodsInventory();
        zlwShopGoodsInventory.setSgiId(UUIDUtils.getOrderIdByUUId("I"));
        zlwShopGoodsInventory.setSgiValue(zlwImportGoodsParam.getSgInventory());
        if(save(zlwShopGoodsInventory)){
            map.put("inventoryId",zlwShopGoodsInventory.getSgiId());
            return true;
        }
        return false;
    }
}
