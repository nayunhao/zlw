package com.zlead.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlead.entity.goods.ZlwShopGoods;
import com.zlead.entity.goods.ZlwShopGoodsSku;
import org.apache.ibatis.annotations.*;
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
public interface ZlwShopGoodsMapper extends BaseMapper<ZlwShopGoods> {
    @Select("SELECT sg_name FROM zlw_shop_goods WHERE sgu_id = #{sguId}")
    @Cacheable(value = "ZlwShopGoods" ,key = "targetClass + methodName +#p0")
    public ZlwShopGoods getShopGoods(String sguId);

    @Select("SELECT count(*) FROM zlw_shop_goods WHERE shop_id = #{shopId} and sg_custom_code = #{customCode}")
    int selectCountByShopIdAndCustomCode(Map<String, Object> map);

    @Select("SELECT * FROM zlw_shop_goods WHERE sg_spu_status = #{sgSpuStatus}  and shop_id = #{shopId}")
    @Cacheable(value = "List<ZlwShopGoods>" ,key = "targetClass + methodName +#p0")
    List<ZlwShopGoods> getGoodsSpuListByStatus(Map<String, Object> map);

    @Select("SELECT spu_code FROM zlw_shop_goods WHERE shop_id = #{shopId} and sg_is_delete =#{sgIsDelete}")
    List<ZlwShopGoods> selectSpuList(Map<String,Object> map);

}
