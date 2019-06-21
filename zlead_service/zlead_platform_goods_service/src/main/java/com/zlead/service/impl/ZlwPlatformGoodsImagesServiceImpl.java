package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwPlatformGoodsImagesMapper;
import com.zlead.entity.goods.ZlwPlatformGoodsImages;
import com.zlead.service.IZlwPlatformGoodsImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZlwPlatformGoodsImagesServiceImpl extends ServiceImpl<ZlwPlatformGoodsImagesMapper, ZlwPlatformGoodsImages> implements IZlwPlatformGoodsImagesService {

    @Override
    public List<ZlwPlatformGoodsImages> getZlwPlatfomrGoodsImagesByPgId(Long pgId) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("pg_id",pgId);
        return (List<ZlwPlatformGoodsImages>)listByMap(map);
    }
}
