package com.zlead.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlead.entity.goods.ZlwShopGoodsBrand;
import org.apache.ibatis.annotations.Select;

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
public interface ZlwShopGoodsBrandMapper extends BaseMapper<ZlwShopGoodsBrand> {

    @Select({"<script>" +
            "SELECT * FROM zlw_shop_goods_brand" +
            "<when test='sgbName!=null '>" +
            " where sgb_name = #{sgbName}" +
            "</when>" +
            "</script>"})
        //@Select("SELECT * FROM zlw_platform_goods_brand")
    List<ZlwShopGoodsBrand> getAllShopGoodsBrand(Map<String, Object> map);

    @Select("SELECT sgb_name FROM zlw_shop_goods_brand WHERE sgb_id = #{sgbId}")
    String getBrandName(String sgbId);
}
