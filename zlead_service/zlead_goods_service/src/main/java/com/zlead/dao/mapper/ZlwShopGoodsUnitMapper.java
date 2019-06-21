package com.zlead.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlead.entity.goods.ZlwShopGoodsUnit;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
public interface ZlwShopGoodsUnitMapper extends BaseMapper<ZlwShopGoodsUnit> {

    @Select("SELECT * FROM zlw_shop_goods_unit")
    List<ZlwShopGoodsUnit> selectAll();
}
