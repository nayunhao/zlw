package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.dao.mapper.ZlwShopGoodsSpecsValueMapper;
import com.zlead.entity.goods.*;
import com.zlead.service.IZlwShopGoodsSpecsValueService;
import com.zlead.utils.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
public class ZlwShopGoodsSpecsValueServiceImpl extends ServiceImpl<ZlwShopGoodsSpecsValueMapper, ZlwShopGoodsSpecsValue> implements IZlwShopGoodsSpecsValueService {

    Logger logger= LoggerFactory.getLogger(ZlwShopGoodsSpecsValueServiceImpl.class);
    @Override
    public boolean insertShopGoodsSpecsValue(Map map) {
        ZlwPlatformGoodsVO zlwPlatformGoodsVO=(ZlwPlatformGoodsVO)map.get("goods");
        ZlwImportGoodsParam zlwImportGoodsParam=(ZlwImportGoodsParam)map.get("param");
        List<ZlwPlatformGoodsSpecsValue> zlwPlatformGoodsSpecsValueList=(List<ZlwPlatformGoodsSpecsValue>)map.get("specsValueList");
        String specsNameId=String.valueOf(map.get("specsNameId"));
        List<ZlwShopGoodsSpecsValue> zlwShopGoodsSpecsValues=new ArrayList<>();
        zlwPlatformGoodsSpecsValueList.forEach(e->{
            ZlwShopGoodsSpecsValue zlwShopGoodsSpecsValue=new ZlwShopGoodsSpecsValue();
            zlwShopGoodsSpecsValue.setSgsnValue(e.getPgsvValue());
            zlwShopGoodsSpecsValue.setSgsnRemark(e.getGsnRemark());
            zlwShopGoodsSpecsValue.setSgsnId(specsNameId);
            zlwShopGoodsSpecsValue.setShopId(zlwImportGoodsParam.getShopId());
            zlwShopGoodsSpecsValue.setSgsvId(UUIDUtils.getOrderIdByUUId("V"));
            zlwShopGoodsSpecsValues.add(zlwShopGoodsSpecsValue);
        });
        if(!CollectionUtils.isEmpty(zlwShopGoodsSpecsValues)) {
            if(saveBatch(zlwShopGoodsSpecsValues, zlwShopGoodsSpecsValues.size())){
                logger.info("规格值信息保存成功");
                return true;
            }else{
                logger.info("规格值信息保存不成功");
                return false;
            }
        }
        logger.info("没有规格值信息");
        return false;
    }
}
