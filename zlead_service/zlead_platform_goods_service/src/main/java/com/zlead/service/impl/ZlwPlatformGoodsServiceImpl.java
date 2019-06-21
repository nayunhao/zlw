package com.zlead.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwPlatformGoodsMapper;
import com.zlead.entity.goods.ZlwPlatformGoods;
import com.zlead.service.IZlwPlatformGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
@Service
public class ZlwPlatformGoodsServiceImpl extends ServiceImpl<ZlwPlatformGoodsMapper, ZlwPlatformGoods> implements IZlwPlatformGoodsService {


    @Autowired
    private ZlwPlatformGoodsMapper zlwPlatformGoodsMapper;

    @Override
    public List<ZlwPlatformGoods> selectpage(Page<ZlwPlatformGoods> page){

        return zlwPlatformGoodsMapper.selectpage(page);
    }

    @Override
    public ZlwPlatformGoods getZlwPlatformGoodsByPgId(Long pgId) {
        return getById(pgId);
    }

    @Override
    public List<ZlwPlatformGoods> selectpageExists(Page<ZlwPlatformGoods> page){
        return zlwPlatformGoodsMapper.selectpageExists(page);
    }







}
