package com.zlead.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlead.entity.goods.ZlwShopGoodsSku;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
public interface ZlwShopGoodsSkuMapper extends BaseMapper<ZlwShopGoodsSku> {

    @Select("SELECT * FROM zlw_shop_goods_sku WHERE sg_status = #{sgStatus}  and shop_id = #{shopId}")
    @Cacheable(value = "List<ZlwShopGoodsSku>" ,key = "targetClass + methodName +#p0")
    List<ZlwShopGoodsSku> getGoodsListByStatus(Map<String, Object> map);

    @Select("SELECT * FROM zlw_shop_goods_sku WHERE shop_id = #{shopId} AND sgu_id=#{sguId}")
    List<ZlwShopGoodsSku> getSkuGoodsListByShopAndSpu(Map<String, Object> map);

    @Select("SELECT * FROM zlw_shop_goods_sku WHERE sgu_id=#{sguId}")
    List<ZlwShopGoodsSku> getSkuGoodsListBySguId(String sguId);


}
