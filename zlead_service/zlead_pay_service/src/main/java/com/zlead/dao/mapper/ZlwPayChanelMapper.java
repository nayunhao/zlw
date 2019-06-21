package com.zlead.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlead.entity.pay.ZlwPayChanel;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 关闭订单原因表 Mapper 接口
 * </p>
 *
 * @author zlw
 * @since 2019-06-20
 */
public interface ZlwPayChanelMapper extends BaseMapper<ZlwPayChanel> {

    @Select("SELECT pc_id, pc_type, pc_name, pc_status, pc_icon FROM zlw_pay_chanel WHERE pc_status = #{status}")
    List<ZlwPayChanel> getByStatus(Map<String, Object> map);

}
