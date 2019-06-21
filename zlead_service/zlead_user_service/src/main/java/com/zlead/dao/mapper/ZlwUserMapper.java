package com.zlead.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlead.entity.user.ZlwUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ZlwUserMapper extends BaseMapper<ZlwUser> {

    @Select("SELECT * FROM zlw_user WHERE user_phone = #{name} OR user_name = #{name}")
    public List<ZlwUser> queryUserByName(String name);

    @Select("UPDATE zlw_user SET user_password=#{userPassword} WHERE user_id=#{userId}")
    public Integer updateUserPassword(ZlwUser zlwUser);

    @Select("SELECT * FROM zlw_user WHERE user_id = #{userId}")
    public ZlwUser getUserById(String userId);
}

