package com.zlead.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlead.constant.ShopsEnum;
import com.zlead.dao.mapper.*;
import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.*;
import com.zlead.service.IZlwShopGoodsService;
import com.zlead.utils.StringUtils;
import com.zlead.utils.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
public class ZlwShopGoodsServiceImpl extends ServiceImpl<ZlwShopGoodsMapper, ZlwShopGoods> implements IZlwShopGoodsService {
    @Autowired
    ZlwShopGoodsMapper zlwShopGoodsMapper;
    @Autowired
    ZlwShopGoodsSkuMapper zlwShopGoodsSkuMapper;
    @Autowired
    ZlwShopGoodsSpecMapper zlwShopGoodsSpecMapper;
    @Autowired
    ZlwShopGoodsPriceMapper zlwShopGoodsPriceMapper;
    @Autowired
    ZlwShopGoodsSpecsNameMapper zlwShopGoodsSpecsNameMapper;
    @Autowired
    ZlwShopGoodsSpecsValueMapper zlwShopGoodsSpecsValueMapper;
    @Autowired
    ZlwShopGoodsImagesMapper zlwShopGoodsImagesMapper;
    @Autowired
    ZlwShopGoodsInventoryMapper zlwShopGoodsInventoryMapper;
    @Autowired
    ZlwShopGoodsClassMapper zlwShopGoodsClassMapper;
    Logger logger= LoggerFactory.getLogger(ZlwShopGoodsServiceImpl.class);
    /**
     * 校验自定义商品编码
     * @param shopId 店铺id
     * @param customCode 商品编码
     * @return
     */
    @Override
    public ApiResult checkCustomInputCode(String shopId, String customCode) {

        if(StringUtils.isEmpty(shopId)){
            return ApiResult.isErrNoToken("店铺参数为空", null);
        }
        if(StringUtils.isEmpty(customCode)){
            return ApiResult.isErrNoToken("商品编码参数为空", null);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("shopId", shopId);
        map.put("customCode", customCode);

        int count = zlwShopGoodsMapper.selectCountByShopIdAndCustomCode(map);
        System.out.println(count);

        ApiResult apiResult = null;

        Map<String, Object> resultMap = new HashMap<>();
        if(count < 1){
            resultMap.put("isSccuess", true);
            apiResult = ApiResult.isOkNoToken("请求成功", resultMap);
        }else{
            resultMap.put("isSccuess", false);
            apiResult = ApiResult.isErrNoToken("编码已存在", resultMap);
        }

        return apiResult;
    }

    /**
     * 添加商品，调用事务
     * @param zlwShopGoods 商品店铺spu表
     * @param zlwShopGoodsSku  商品店铺sku表
     * @param zlwShopGoodsSpec  店铺SKU-商品规格表
     * @param zlwShopGoodsPrice 店铺SKU-商品价格表
     * @param zlwShopGoodsSpecsName 店铺SKU-商品名称规格表
     * @param zlwShopGoodsSpecsValue 店铺SKU-商品规格值表
     * @param zlwShopGoodsImages 店铺SKU-商品图片表
     * @param zlwShopGoodsInventory 店铺SKU-商品库存表
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.DEFAULT,readOnly = false,rollbackFor=Exception.class)
    public void addGoodsTrans(ZlwShopGoods zlwShopGoods, ZlwShopGoodsSku zlwShopGoodsSku, ZlwShopGoodsSpec zlwShopGoodsSpec, ZlwShopGoodsPrice zlwShopGoodsPrice, ZlwShopGoodsSpecsName zlwShopGoodsSpecsName, ZlwShopGoodsSpecsValue zlwShopGoodsSpecsValue, ZlwShopGoodsImages zlwShopGoodsImages, ZlwShopGoodsInventory zlwShopGoodsInventory) throws Exception{
        zlwShopGoodsMapper.insert(zlwShopGoods);
        zlwShopGoodsSkuMapper.insert(zlwShopGoodsSku);
        zlwShopGoodsSpecMapper.insert(zlwShopGoodsSpec);
        zlwShopGoodsPriceMapper.insert(zlwShopGoodsPrice);
        zlwShopGoodsSpecsNameMapper.insert(zlwShopGoodsSpecsName);
        String s=null;
        s.length();//Exception
        zlwShopGoodsSpecsValueMapper.insert(zlwShopGoodsSpecsValue);
        zlwShopGoodsImagesMapper.insert(zlwShopGoodsImages);
        zlwShopGoodsInventoryMapper.insert(zlwShopGoodsInventory);
    }

    @Override
    public boolean insertShopGoods(Map map) {

        ZlwPlatformGoodsVO zlwPlatformGoodsVO=(ZlwPlatformGoodsVO)map.get("goods");
        ZlwImportGoodsParam zlwImportGoodsParam=(ZlwImportGoodsParam)map.get("param");
        QueryWrapper<ZlwShopGoods> qw=new QueryWrapper();
        qw.eq("spu_code",zlwPlatformGoodsVO.getSpuCode());
        qw.eq("shop_id",zlwImportGoodsParam.getShopId());
        ZlwShopGoods sg=getOne(qw);
        if(sg!=null){
            throw new RuntimeException("商铺表已经存在此商品");
        }
        ZlwShopGoods zlwShopGoods=new ZlwShopGoods();
        zlwShopGoods.setSguId(UUIDUtils.getOrderIdByUUId("P"));
        zlwShopGoods.setSgName(zlwPlatformGoodsVO.getPgName());
        zlwShopGoods.setSpuCode(zlwPlatformGoodsVO.getSpuCode());
        zlwShopGoods.setSgClass1(Long.valueOf(zlwImportGoodsParam.getShopClass1()));
        zlwShopGoods.setSgClass2(Long.valueOf(zlwImportGoodsParam.getShopClass2()));
        zlwShopGoods.setShopId(zlwImportGoodsParam.getShopId());
        zlwShopGoods.setSgSalesTarget("1");//1平台，2店铺
        zlwShopGoods.setSgOptionTime(System.currentTimeMillis());
        zlwShopGoods.setSgIsDelete(2);
        zlwShopGoods.setSgIsLock(2);
        zlwShopGoods.setPgbId(zlwPlatformGoodsVO.getPgbId());
        if(save(zlwShopGoods)){
            map.put("spuId",zlwShopGoods.getSguId());
            logger.info("店铺商品spu保存成功");
            return true;
        }
        logger.info("店铺商品spu保存失败");
        return false;
    }

    /**
     * 商品首页列表 SPU (备)
     * @param map
     * @return
     */
    @Override
    public Map<String,Page<ZlwShopGoods>> getGoodsSpuListByStatus(Map<String, Object> map) {

        List<ZlwShopGoods> goodsSpuList= zlwShopGoodsMapper.getGoodsSpuListByStatus(map);
        Page<ZlwShopGoods> page = (Page<ZlwShopGoods>) map.get("page");

        goodsSpuList.stream().forEach(
                goodsSpu -> {
                    //获得spu的ID
                    String sguId = goodsSpu.getSguId();
                    //店铺ID
                    String shopId = goodsSpu.getShopId();
                    Map<String,Object> spuPara = new HashMap<>();
                    spuPara.put("sguId",sguId);
                    spuPara.put("shopId",shopId);
                    List<ZlwShopGoodsSku> skus = zlwShopGoodsSkuMapper.getSkuGoodsListByShopAndSpu(spuPara);

                    skus.stream().forEach(
                            goodsSku ->{
                                //根据sguId查询spu名称
                                String sgName = goodsSpu.getSgName();
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

                    goodsSpu.setSkus(skus);
                }
        );
        page.setRecords(goodsSpuList);
        Map<String,Page<ZlwShopGoods>> pageMap =new HashMap<>();
        pageMap.put(ShopsEnum.S_GOODS_ENUM.getValue(),page);
        return pageMap;
    }


    @Override
    public  List<ZlwShopGoods> selectSpuList(String shopId,Integer sgIsDelete){
        Map<String,Object> map=new HashMap<>();
        map.put("shopId",shopId);
        map.put("sgIsDelete",sgIsDelete);
        return  zlwShopGoodsMapper.selectSpuList(map);
    }



}
