package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwPlatformGoodsSpecsValueMapper;
import com.zlead.entity.goods.ZlwPlatformGoodsSpecsValue;
import com.zlead.service.IZlwPlatformGoodsSpecsValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
@Service
public class ZlwPlatformGoodsSpecsValueServiceImpl extends ServiceImpl<ZlwPlatformGoodsSpecsValueMapper, ZlwPlatformGoodsSpecsValue> implements IZlwPlatformGoodsSpecsValueService {

    @Override
    public List<ZlwPlatformGoodsSpecsValue> getZlwPlatformGoodsSpecsValueByPgsnId(String pgsnId) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("pgsn_id",pgsnId);
        List<ZlwPlatformGoodsSpecsValue> zlwPlatformGoodsSpecsValueList=(List<ZlwPlatformGoodsSpecsValue>)listByMap(map);
        return zlwPlatformGoodsSpecsValueList;
    }

}
