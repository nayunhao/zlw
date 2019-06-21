package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwPayChanelMapper;
import com.zlead.entity.pay.ZlwPayChanel;
import com.zlead.service.IZlwPayChanelService;
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
public class ZlwPayChanelServiceImpl extends ServiceImpl<ZlwPayChanelMapper, ZlwPayChanel> implements IZlwPayChanelService {
    @Autowired
    ZlwPayChanelMapper zlwPayChanelMapper;


    @Override
    public List<ZlwPayChanel> getUseablePayChanel() {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("status", 1);

        List<ZlwPayChanel> list = zlwPayChanelMapper.getByStatus(paramMap);
        return list;
    }

}
