package com.zlead.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlead.entity.shop.ZlwShop;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ZlwShopMapper extends BaseMapper<ZlwShop> {

    @Select("SELECT * FROM zlw_shop WHERE company_id = #{companyId}")
    public List<ZlwShop> getShopsByCompanyId(String companyId);
}
