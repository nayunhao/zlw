package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwPlatformGoodsManufacturerMapper;
import com.zlead.entity.goods.ZlwPlatformGoodsManufacturer;
import com.zlead.service.IZlwPlatformGoodsManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
public class ZlwPlatformGoodsManufacturerServiceImpl extends ServiceImpl<ZlwPlatformGoodsManufacturerMapper, ZlwPlatformGoodsManufacturer> implements IZlwPlatformGoodsManufacturerService {


    @Override
    public ZlwPlatformGoodsManufacturer getZlwPlatformGoodsManufacturerByPgId(Long pgmId) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("pgm_id",pgmId);
        List<ZlwPlatformGoodsManufacturer> zlwPlatformGoodsManufacturerList=(List<ZlwPlatformGoodsManufacturer>)listByMap(map);
        if(!CollectionUtils.isEmpty(zlwPlatformGoodsManufacturerList)){
            return zlwPlatformGoodsManufacturerList.get(0);
        }
        return null;
    }
}
