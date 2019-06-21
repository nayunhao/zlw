package com.zlead.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlead.entity.goods.ZlwPlatformGoods;
import com.zlead.entity.goods.ZlwShopGoods;
import com.zlead.entity.goods.ZsgZpg;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
public interface ZlwPlatformGoodsMapper extends BaseMapper<ZlwPlatformGoods> {


    @Select("SELECT zpg.pg_id,zpg.spu_code,zpg.pg_codebar,zpg.pg_name,zpg.pgc_id1,zpg.pgc_id2,zpg.pgc_id3,zpg.pgm_id,zpg.pgb_id,zpg.pg_remark,zpgi.pg_image_url,zpgi.pg_image_desc FROM  zlw_platform_goods zpg ,zlw_platform_goods_images zpgi WHERE zpgi.pg_id=zpg.pg_id")
    List<ZlwPlatformGoods> selectpage(Page<ZlwPlatformGoods> page);


    @Select("SELECT zpg.pg_id,zpg.spu_code,zpg.pg_codebar,zpg.pg_name,zpg.pgc_id1,zpg.pgc_id2,zpg.pgc_id3,zpg.pgm_id,zpg.pgb_id,zpg.pg_remark,zpgi.pg_image_url,zpgi.pg_image_desc FROM   zlw_platform_goods_images zpgi LEFT JOIN  zlw_platform_goods  zpg   ON zpgi.pg_id=zpg.pg_id  WHERE NOT  EXISTS (SELECT 1 FROM zsg_zpg zz WHERE zz.spu_code=zpg.spu_code)")
    List<ZlwPlatformGoods> selectpageExists(Page<ZlwPlatformGoods> page);






}
