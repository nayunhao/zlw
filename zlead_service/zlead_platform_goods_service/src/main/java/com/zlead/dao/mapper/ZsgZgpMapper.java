package com.zlead.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlead.entity.goods.ZlwPlatformGoods;
import com.zlead.entity.goods.ZsgZpg;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
public interface ZsgZgpMapper extends BaseMapper<ZsgZpg> {



    @Update({"create table zsg_zpg(  zsg_zpg_id int PRIMARY KEY NOT NULL AUTO_INCREMENT," +
            " spu_code varchar(100) NOT NULL," +
            "index(spu_code))"+
            " ENGINE=InnoDB DEFAULT CHARSET=utf8"})
     void createTableZlw();


    @Update("DROP TABLE zsg_zpg")
    void delTableZlw();

//    @Insert("INSERT INTO zsg_zpg ( spu_code ) VALUSE <foreach collection='List' item='collect' index='index' separator=',' >" +
//            "(#{collect.spuCode})</foreach> ")
//    int insertZsgZpgList(List collect);






}
