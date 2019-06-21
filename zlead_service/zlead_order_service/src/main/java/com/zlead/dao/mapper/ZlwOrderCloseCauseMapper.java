package com.zlead.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlead.entity.order.ZlwOrderCloseCause;
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
public interface ZlwOrderCloseCauseMapper extends BaseMapper<ZlwOrderCloseCause> {

    @Select("SELECT occ_id, occ_type, occ_name, occ_status FROM zlw_order_close_cause WHERE occ_status = #{status}")
    List<ZlwOrderCloseCause> getCloseOrderCauseByStatus(Map<String, Object> map);

}
