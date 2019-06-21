package com.zlead.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlead.entity.goods.ZlwShopGoodsInventory;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
public interface ZlwShopGoodsInventoryMapper extends BaseMapper<ZlwShopGoodsInventory> {
    @Select("SELECT sgi_value FROM  `zlw_shop_goods_inventory` WHERE sgi_id = #{sgiId}")
    String  getInventoryValue(String sgiId);
}
