package com.zlead.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlead.entity.company.ZlwCompany;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ZlwCompanyMapper extends BaseMapper<ZlwCompany> {

    @Select("SELECT * FROM zlw_company WHERE user_id = #{userId}")
    public List<ZlwCompany> getCompanysByUser(String userId);
}
