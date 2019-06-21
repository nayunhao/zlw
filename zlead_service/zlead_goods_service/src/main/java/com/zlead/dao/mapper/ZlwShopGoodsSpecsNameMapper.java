package com.zlead.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlead.entity.goods.ZlwShopGoodsSpecsName;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
public interface ZlwShopGoodsSpecsNameMapper extends BaseMapper<ZlwShopGoodsSpecsName> {
    @Select("SELECT sgsn_name FROM zlw_shop_goods_specs_name WHERE sgsn_id = #{sgsnId}")
    String getShopGoodsSpecsName(String sgsnId);
}
