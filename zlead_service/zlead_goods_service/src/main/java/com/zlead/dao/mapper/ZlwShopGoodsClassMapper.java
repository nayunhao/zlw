package com.zlead.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlead.entity.goods.ZlwShopGoodsClass;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Select;
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
public interface ZlwShopGoodsClassMapper extends BaseMapper<ZlwShopGoodsClass> {


    @Select("SELECT * FROM zlw_shop_goods_class  WHERE  sgc_parent_id  = #{sgcParentId} AND shop_id  = #{shopId}")
    List<ZlwShopGoodsClass> selectListAll(Map<String,Object> map);

    @Select("SELECT sgc_name FROM zlw_shop_goods_class WHERE sgc_id = #{sgcId}")
    String getClassName(Long sgcId);

    @Select("UPDATE zlw_shop_goods_class SET sgc_is_delete = #{sgcIsDelete} WHERE sgc_parent_id=#{sgcId}")
    void updateByParentId(ZlwShopGoodsClass zlwShopGoodsClass);

    @Select("SELECT * FROM zlw_shop_goods_class WHERE sgc_name = #{sgcName} AND sgc_level = #{sgcLevel}")
    List<ZlwShopGoodsClass> selectByName(Map map);

    @Select("SELECT * FROM zlw_shop_goods_class ")
    List<ZlwShopGoodsClass> selectListAllTree();
}
