package com.zlead.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlead.entity.goods.ZlwShopGoodsSpecsValue;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
public interface ZlwShopGoodsSpecsValueMapper extends BaseMapper<ZlwShopGoodsSpecsValue> {
    @Select("SELECT sgsn_value FROM zlw_shop_goods_specs_value WHERE sgsv_id = #{sgsvId}")
    String  getGoodsSpecValue(String sgsvId);
}
