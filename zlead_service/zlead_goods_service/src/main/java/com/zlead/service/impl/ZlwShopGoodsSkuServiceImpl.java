package com.zlead.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.constant.ShopsEnum;
import com.zlead.dao.mapper.*;
import com.zlead.entity.goods.ZlwImportGoodsParam;
import com.zlead.entity.goods.ZlwPlatformGoodsVO;
import com.zlead.entity.goods.ZlwShopGoodsPrice;
import com.zlead.entity.goods.ZlwShopGoodsSku;
import com.zlead.entity.goods.ZlwShopGoodsSpec;
import com.zlead.service.IZlwShopGoodsSkuService;
import com.zlead.utils.StringUtils;
import com.zlead.utils.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
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
public class ZlwShopGoodsSkuServiceImpl extends ServiceImpl<ZlwShopGoodsSkuMapper, ZlwShopGoodsSku> implements IZlwShopGoodsSkuService {

    Logger logger= LoggerFactory.getLogger(ZlwShopGoodsSkuServiceImpl.class);

    @Override
    public Map<String,Page<ZlwShopGoodsSku>> getGoodsListByStatus(Map<String, Object> map) {

        List<ZlwShopGoodsSku> goodsSkuList = zlwShopGoodsSkuMapper.getGoodsListByStatus(map);

        Page<ZlwShopGoodsSku> page = (Page<ZlwShopGoodsSku>) map.get("page");


        goodsSkuList.forEach(
                goodsSku ->{
                    //根据sguId查询spu名称
                    String sgName = zlwShopGoodsMapper.getShopGoods(goodsSku.getSguId()).getSgName();
                    goodsSku.setSgName(sgName);
                    //获取库存
                    String inventoryValue = zlwShopGoodsInventoryMapper.getInventoryValue(goodsSku.getSgiId());
                    //获取价格
                    ZlwShopGoodsPrice shopGoodsPrice =zlwShopGoodsPriceMapper.getPrice(goodsSku.getSgpId());

                    //获取分类名称
                    String className1=zlwShopGoodsClassMapper.getClassName(goodsSku.getSgClass1());
                    String className2=zlwShopGoodsClassMapper.getClassName(goodsSku.getSgClass2());
                    //获取sku规格信息
                    ZlwShopGoodsSpec shopGoodsSpecs =zlwShopGoodsSpecMapper.getGoodsSpec(goodsSku.getSgCode());

                    goodsSku.setGoodSpecName(zlwShopGoodsSpecsNameMapper.getShopGoodsSpecsName(shopGoodsSpecs.getSgsnId()));
                    goodsSku.setGoodSpecValue(zlwShopGoodsSpecsValueMapper.getGoodsSpecValue(shopGoodsSpecs.getSgsvId()));
                    goodsSku.setSgClassName1(className1);
                    goodsSku.setSgClassName2(className2);
                    goodsSku.setInventoryValue(inventoryValue);
                    goodsSku.setShopGoodsPrice(shopGoodsPrice);
                }
        );

        page.setRecords(goodsSkuList);
        Map<String,Page<ZlwShopGoodsSku>> pageMap=new HashMap<>();
        pageMap.put(ShopsEnum.S_GOODS_ENUM.getValue(),page);
        return pageMap;
    }

    @Autowired
    private ZlwShopGoodsSpecMapper zlwShopGoodsSpecMapper;

    @Autowired
    private ZlwShopGoodsSkuMapper zlwShopGoodsSkuMapper;

    @Autowired
    private ZlwShopGoodsMapper zlwShopGoodsMapper;

    @Autowired
    private ZlwShopGoodsClassMapper zlwShopGoodsClassMapper;
    @Override
    public int batchEditGoodsStatus(String goodsIds, String type) {
        try {
            String[] goodsArr = goodsIds.split(",");
            for (String goodsId:goodsArr){
                ZlwShopGoodsSku model = baseMapper.selectById(goodsId);
                if("delete".equals(type)){//删除
                    model.setSgIsDelete(1);
                }else if("under".equals(type)){//下架
                    model.setSgStatus(2);
                }else if("up".equals(type)){//上架
                    //获取库存(判断无库存不能上架)
                    String inventoryValue = zlwShopGoodsInventoryMapper.getInventoryValue(model.getSgiId());
                    if(StringUtils.isNotBlank(inventoryValue)){
                        int i = Integer.parseInt(inventoryValue);
                        if(i<1){
                            logger.info("该商品库存为零!");
                            return 1;
                        }else{
                            model.setSgStatus(1);
                        }
                    }else {
                        logger.info("该商品无库存!");
                        return 2;
                    }
                }else if ("top".equals(type)){//置顶
                    model.setSgSort(2L);
                    model.setSgSortTime(new Date()); //置顶时间
                }else if ("cancelTop".equals(type)){//取消置顶
                    model.setSgSort(1L);
                }else {
                    return 3;
                }
                this.baseMapper.updateById(model);
            }
        } catch (Exception e){
            e.printStackTrace();
            return 4;
        }
        return 0;
    }

    @Override
    public boolean batchSetGoodsClass(Map<String,Object> data) {
        try {
            String goodsIds  = (String)data.get("sgGoodsIds");
            Integer sgClassId1 = (Integer)data.get("sgClassId1");
            Integer sgClassId2 = (Integer)data.get("sgClassId2");
            String[] goodsArr = goodsIds.split(",");
            ZlwShopGoodsSku model = null;
            for (String goodsId:goodsArr){
                model = new ZlwShopGoodsSku();
                model.setSgkId(goodsId);
                model.setSgClass1(sgClassId1.longValue());
                model.setSgClass2(sgClassId2.longValue());
                this.baseMapper.updateById(model);
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean insertShopGoodsSku(Map map) {
        ZlwPlatformGoodsVO zlwPlatformGoodsVO=(ZlwPlatformGoodsVO)map.get("goods");
        ZlwImportGoodsParam zlwImportGoodsParam=(ZlwImportGoodsParam)map.get("param");
        String sguId=String.valueOf(map.get("spuId"));
        ZlwShopGoodsSku zlwShopGoodsSku=new ZlwShopGoodsSku();
        zlwShopGoodsSku.setSgkId(UUIDUtils.getOrderIdByUUId("K"));
        zlwShopGoodsSku.setSgName(zlwPlatformGoodsVO.getPgName());
        zlwShopGoodsSku.setSguId(sguId);
        zlwShopGoodsSku.setSpuCode(zlwPlatformGoodsVO.getSpuCode());
        zlwShopGoodsSku.setSgClass1(Long.valueOf(zlwImportGoodsParam.getShopClass1()));
        zlwShopGoodsSku.setSgClass2(Long.valueOf(zlwImportGoodsParam.getShopClass2()));
        zlwShopGoodsSku.setSgCreateTime(System.currentTimeMillis());
        zlwShopGoodsSku.setSgIsDelete(2);
        zlwShopGoodsSku.setSgIsLock(2);
        zlwShopGoodsSku.setShopId(zlwImportGoodsParam.getShopId());
        zlwShopGoodsSku.setSgSalesTarget(1);//1平台，2店铺
        zlwShopGoodsSku.setSgpId(String.valueOf(map.get("priceId")));
        zlwShopGoodsSku.setSgiId(String.valueOf(map.get("inventoryId")));
        if(save(zlwShopGoodsSku)){
            map.put("skuId",zlwShopGoodsSku.getSgkId());
            logger.info("店铺商品sku保存成功");
            return true;
        }
        logger.info("店铺商品sku保存失败");
        return false;
    }



    @Autowired
    private ZlwShopGoodsPriceMapper zlwShopGoodsPriceMapper;

    @Autowired
    private ZlwShopGoodsInventoryMapper zlwShopGoodsInventoryMapper;

    @Autowired
    private ZlwShopGoodsSpecsNameMapper zlwShopGoodsSpecsNameMapper;

    @Autowired
    private ZlwShopGoodsSpecsValueMapper zlwShopGoodsSpecsValueMapper;


}
