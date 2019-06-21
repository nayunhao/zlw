package com.zlead.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlead.entity.goods.ZlwPlatformGoodsClass;
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
public interface ZlwPlatformGoodsClassMapper extends BaseMapper<ZlwPlatformGoodsClass> {

    @Select("SELECT pgc_id, pgc_name, pgc_level, pgc_parent_id, pgc_remark FROM zlw_platform_goods_class WHERE pgc_parent_id = #{pgcParentId}")
    List<ZlwPlatformGoodsClass> getPlatFormSonClass(String pgcParentId);

    @Select("SELECT pgc_id, pgc_name, pgc_level, pgc_parent_id, pgc_remark FROM zlw_platform_goods_class WHERE pgc_level = #{pgcLevel}")
    List<ZlwPlatformGoodsClass> getPlatFormClassByLevel(Integer pgcLevel);

}
