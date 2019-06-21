package com.zlead.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlead.entity.goods.ZlwShopGoodsSpec;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
public interface ZlwShopGoodsSpecMapper extends BaseMapper<ZlwShopGoodsSpec> {
    @Select("SELECT * FROM zlw_shop_goods_spec")
    ZlwShopGoodsSpec getGoodsSpec(String sgCode);
}
