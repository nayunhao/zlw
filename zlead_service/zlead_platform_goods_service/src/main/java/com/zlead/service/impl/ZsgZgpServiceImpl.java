package com.zlead.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwPlatformGoodsMapper;
import com.zlead.dao.mapper.ZsgZgpMapper;
import com.zlead.entity.goods.ZlwPlatformGoods;
import com.zlead.entity.goods.ZsgZpg;
import com.zlead.service.IZlwPlatformGoodsService;
import com.zlead.service.ZsgZgpService;
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
public class ZsgZgpServiceImpl extends ServiceImpl<ZsgZgpMapper, ZsgZpg> implements ZsgZgpService {

    @Autowired
    private ZsgZgpMapper zsgZgpMapper;






    /**
     * 创建zsg_zpg表
     */
    @Override
    public void createTableZlw(){
        zsgZgpMapper.createTableZlw();
    }

    /**
     * 删除zsg_zpg表
     */
    @Override
    public void delTableZlw(){
        zsgZgpMapper.delTableZlw();
    }

    /**
     * 批量插入临时数据
     */

    @Override
    public  boolean insertZsgZpgList(List collect){
       // return zsgZgpMapper.insertZsgZpgList(collect);
        return  saveBatch(collect,collect.size());
    }





}
