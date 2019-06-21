package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwShopGoodsSpecsGroupMapper;
import com.zlead.entity.goods.*;
import com.zlead.service.IZlwShopGoodsSpecsGroupService;
import com.zlead.service.IZlwShopGoodsSpecsNameService;
import com.zlead.utils.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zlw
 * @since 2019-05-31
 */
@Service
public class ZlwShopGoodsSpecsGroupServiceImpl extends ServiceImpl<ZlwShopGoodsSpecsGroupMapper, ZlwShopGoodsSpecsGroup> implements IZlwShopGoodsSpecsGroupService {

    Logger logger= LoggerFactory.getLogger(ZlwShopGoodsSpecsGroupServiceImpl.class);
    @Autowired
    private IZlwShopGoodsSpecsNameService zlwShopGoodsSpecsNameService;
    @Override
    public boolean insertShopGoodsSpecsGroup(Map map) {
        ZlwPlatformGoodsVO zlwPlatformGoodsVO=(ZlwPlatformGoodsVO)map.get("goods");
        ZlwImportGoodsParam zlwImportGoodsParam=(ZlwImportGoodsParam)map.get("param");
        List<ZlwPlatformGoodsSpecsGroupVO> zlwPlatformGoodsSpecsGroupList=zlwPlatformGoodsVO.getZlwPlatformGoodsSpecsGroupVOList();
        List<ZlwShopGoodsSpecsGroup> zlwShopGoodsSpecsGroups=new ArrayList<>();
        String shopClass="";
        if(StringUtils.isEmpty(zlwImportGoodsParam.getShopClass1())){
            if(!StringUtils.isEmpty(zlwImportGoodsParam.getShopClass2())){
                shopClass=zlwImportGoodsParam.getShopClass2();
            }
        }else{
            if(StringUtils.isEmpty(zlwImportGoodsParam.getShopClass2())){
                shopClass=zlwImportGoodsParam.getShopClass1();
            }else{
                shopClass=zlwImportGoodsParam.getShopClass2();
            }
        }
        String sc=shopClass;
        zlwPlatformGoodsSpecsGroupList.forEach(e->{
            ZlwShopGoodsSpecsGroup zlwShopGoodsSpecsGroup=new ZlwShopGoodsSpecsGroup();
            zlwShopGoodsSpecsGroup.setSgsgId(UUIDUtils.getOrderIdByUUId("G"));
            zlwShopGoodsSpecsGroup.setShopId(zlwImportGoodsParam.getShopId());
            zlwShopGoodsSpecsGroup.setSgcId(sc);
            zlwShopGoodsSpecsGroup.setSgsgName(e.getPgsgName());
            zlwShopGoodsSpecsGroup.setSgsnRemark(e.getPgsnRemark());
            zlwShopGoodsSpecsGroups.add(zlwShopGoodsSpecsGroup);
            Map map1=new HashMap<String,Object>();
            map1.put("specGroupId",zlwShopGoodsSpecsGroup.getSgsgId());
            map1.put("specNameList",e.getZlwPlatformGoodsSpecsNameVOList());
            map1.putAll(map);
            zlwShopGoodsSpecsNameService.insertShopGoodsSpecsName(map1);
        });
        if(!CollectionUtils.isEmpty(zlwShopGoodsSpecsGroups)) {
           if(saveBatch(zlwShopGoodsSpecsGroups, zlwShopGoodsSpecsGroups.size())){
               logger.info("规格组信息保存成功");
               return true;
           }else{
               logger.info("规格组信息保存不成功");
               return false;
           }
        }
        logger.info("没有规格组信息");
        return false;
    }
}
