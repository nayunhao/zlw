package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwShopGoodsSpecsNameMapper;
import com.zlead.entity.goods.ZlwImportGoodsParam;
import com.zlead.entity.goods.ZlwPlatformGoodsSpecsNameVO;
import com.zlead.entity.goods.ZlwPlatformGoodsVO;
import com.zlead.entity.goods.ZlwShopGoodsSpecsName;
import com.zlead.service.IZlwShopGoodsSpecsNameService;
import com.zlead.service.IZlwShopGoodsSpecsValueService;
import com.zlead.utils.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
public class ZlwShopGoodsSpecsNameServiceImpl extends ServiceImpl<ZlwShopGoodsSpecsNameMapper, ZlwShopGoodsSpecsName> implements IZlwShopGoodsSpecsNameService {

    Logger logger= LoggerFactory.getLogger(ZlwShopGoodsSpecsNameServiceImpl.class);
    @Autowired
    private IZlwShopGoodsSpecsValueService zlwShopGoodsSpecsValueService;
    @Override
    public boolean insertShopGoodsSpecsName(Map map) {
        List<ZlwPlatformGoodsSpecsNameVO> zlwPlatformGoodsSpecsNameVOList=(List<ZlwPlatformGoodsSpecsNameVO>)map.get("specNameList");
        String specsGroupId=String.valueOf(map.get("specGroupId"));
        ZlwImportGoodsParam zlwImportGoodsParam=(ZlwImportGoodsParam)map.get("param");
        List<ZlwShopGoodsSpecsName> zlwShopGoodsSpecsNames=new ArrayList<>();
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
        zlwPlatformGoodsSpecsNameVOList.forEach(e->{
            ZlwShopGoodsSpecsName zlwShopGoodsSpecsName=new ZlwShopGoodsSpecsName();
            zlwShopGoodsSpecsName.setSgsnName(e.getPgsnName());
            zlwShopGoodsSpecsName.setSgsnRemark(e.getPgsnRemark());
            zlwShopGoodsSpecsName.setSgcId(sc);
            zlwShopGoodsSpecsName.setSgsgId(specsGroupId);
            zlwShopGoodsSpecsName.setSgsnId(UUIDUtils.getOrderIdByUUId("N"));
            zlwShopGoodsSpecsName.setShopId(zlwImportGoodsParam.getShopId());
            zlwShopGoodsSpecsNames.add(zlwShopGoodsSpecsName);
            Map map1=new HashMap<String,Object>();
            map1.put("specsNameId",zlwShopGoodsSpecsName.getSgsnId());
            map1.put("specsValueList",e.getZlwPlatformGoodsSpecsValueList());
            map1.putAll(map);
            zlwShopGoodsSpecsValueService.insertShopGoodsSpecsValue(map);
        });
        if(!CollectionUtils.isEmpty(zlwShopGoodsSpecsNames)) {
            if(saveBatch(zlwShopGoodsSpecsNames, zlwShopGoodsSpecsNames.size())){
                logger.info("规格名信息保存成功");
                return true;
            }else{
                logger.info("规格名信息保存不成功");
                return false;
            }
        }
        logger.info("没有规格名信息");
        return false;
    }
}
