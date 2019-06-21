package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwOrderCloseCauseMapper;
import com.zlead.entity.order.ZlwOrderCloseCause;
import com.zlead.service.IZlwOrderCloseCauseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 关闭订单原因表 服务实现类
 * </p>
 *
 * @author zlw
 * @since 2019-06-20
 */
@Service
public class ZlwOrderCloseCauseServiceImpl extends ServiceImpl<ZlwOrderCloseCauseMapper, ZlwOrderCloseCause> implements IZlwOrderCloseCauseService {
    @Autowired
    ZlwOrderCloseCauseMapper zlwOrderCloseCauseMapper;

    @Override
    public List<ZlwOrderCloseCause> getUseableCloseOrderCause() {

        Map<String, Object> paramMap = new HashMap<>();
        List<ZlwOrderCloseCause> list = zlwOrderCloseCauseMapper.getCloseOrderCauseByStatus(paramMap);
        return list;
    }
}
