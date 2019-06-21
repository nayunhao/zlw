package com.zlead.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlead.entity.goods.ZlwShopGoodsPrice;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
public interface ZlwShopGoodsPriceMapper extends BaseMapper<ZlwShopGoodsPrice> {

    @Select("SELECT * FROM zlw_shop_goods_price WHERE sgp_id = #{spgId}")
    ZlwShopGoodsPrice getPrice(String sgpId);
}
