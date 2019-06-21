package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwPlatformGoodsSpecsMapper;
import com.zlead.entity.goods.ZlwPlatformGoodsSpecs;
import com.zlead.service.IZlwPlatformGoodsSpecsService;
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
public class ZlwPlatformGoodsSpecsServiceImpl extends ServiceImpl<ZlwPlatformGoodsSpecsMapper, ZlwPlatformGoodsSpecs> implements IZlwPlatformGoodsSpecsService {

    @Autowired
    private ZlwPlatformGoodsSpecsMapper zlwPlatformGoodsSpecsMapper;
    @Override
    public List<ZlwPlatformGoodsSpecs> getZlwPlatformGoodsSpecsByPgId(Long pgId) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("pgId",pgId);
        List<ZlwPlatformGoodsSpecs> zlwPlatformGoodsSpecsList=zlwPlatformGoodsSpecsMapper.selectByMap(map);
        return zlwPlatformGoodsSpecsList;
    }
}
