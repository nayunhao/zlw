package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwPlatformGoodsBrandMapper;
import com.zlead.entity.goods.ZlwPlatformGoodsBrand;
import com.zlead.service.IZlwPlatformGoodsBrandService;
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
public class ZlwPlatformGoodsBrandServiceImpl extends ServiceImpl<ZlwPlatformGoodsBrandMapper, ZlwPlatformGoodsBrand> implements IZlwPlatformGoodsBrandService {

    @Override
    public ZlwPlatformGoodsBrand getZlwPlatformGoodsBrandByPgId(Long pgbId) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("pgb_id",pgbId);
        List<ZlwPlatformGoodsBrand> zlwPlatformGoodsBrandList=(List<ZlwPlatformGoodsBrand>)listByMap(map);
        if(!CollectionUtils.isEmpty(zlwPlatformGoodsBrandList)){
            return zlwPlatformGoodsBrandList.get(0);
        }
        return null;
    }
}
