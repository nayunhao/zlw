package com.zlead.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlead.constant.ShopsEnum;
import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.*;
import com.zlead.service.IZlwShopGoodsClassService;
import com.zlead.service.IZlwShopGoodsService;
import com.zlead.service.IZlwShopGoodsSkuService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ZlwGoods")
@Slf4j
public class ZlwGoodsController {

    @Autowired
    private IZlwShopGoodsClassService iZlwShopGoodsClassService;
    @Autowired
    IZlwShopGoodsService iZlwShopGoodsService;

    @Autowired
    private IZlwShopGoodsSkuService zlwShopGoodsSkuService;

    /* *
     * @Author GuoYunFei
     * @Description //批量修改商品状态
     * @Date 下午 3:55 2019/6/5 0005
     * @Param [data]
     * @return boolean
     */
    @PostMapping("/editGoodsStatus")
    public int editGoodsStatus(@RequestBody Map data){
        String goodsIds = (String) data.get("sgGoodsIds");
        String type = (String) data.get("sgType");
        return zlwShopGoodsSkuService.batchEditGoodsStatus(goodsIds, type);
    }

    /* *
     * @Author GuoYunFei
     * @Description //批量修改商品分类
     * @Date 下午 3:55 2019/6/5 0005
     * @Param [data]
     * @return boolean
     */
    @PostMapping("/batchSetGoodsClass")
    public boolean setGoodsClass(@RequestBody Map data){
        boolean result =  zlwShopGoodsSkuService.batchSetGoodsClass(data);
        return result;
    }

    /* *
     * @Author GuoYunFei
     * @Description //修改商品分类
     * @Date 下午 6:56 2019/6/5 0005
     * @Param
     * @return
     */
    @PostMapping("/editShopGoodsClass")
    public int editShopGoodsClass(@RequestBody Map data) {
        int result = iZlwShopGoodsClassService.editShopGoodsClass(data);
        return result;
    }

    /* *
     * @Author GuoYunFei
     * @Description //删除店铺分类
     * @Date 下午 7:07 2019/6/5 0005
     * @Param
     * @return
     */
    @PostMapping("/removeShopGoodsClass/{sgcId}")
    public boolean removeShopGoodsClass(@PathVariable("sgcId") String sgcId) {
        boolean result = iZlwShopGoodsClassService.removeShopGoodsClass(sgcId);
        return result;
    }

    @Autowired
    private IZlwShopGoodsSkuService iZlwShopGoodsSkuService;
    /**
     * 添加店铺分类
     * @param zlwShopGoodsClass
     * @return
     */
    @PostMapping("/addShopGoodsClass")
    public boolean addShopGoodsClass(@RequestBody ZlwShopGoodsClass zlwShopGoodsClass){
        boolean result = iZlwShopGoodsClassService.save(zlwShopGoodsClass);
        return result;
    }

    /**
     * 获取商品首页列表信息
     * @param data
     * @return
     */
    @PostMapping("/getGoodsListByStatus")
    public Map<String,Page<ZlwShopGoodsSku>> getGoodsListByStatus(@RequestBody Map data){
        Integer sgStatus = (Integer) data.get("sgStatus");
        String shopId = (String) data.get("shopId");
        Long currentPage = Long.valueOf((String) data.get("currentPage"));
        Long sizePage = Long.valueOf((String) data.get("sizePage"));
        Page<ZlwShopGoodsSku> page = new Page<>(currentPage, sizePage);

        Map<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("sgStatus",sgStatus);
        map.put("shopId",shopId);
        Logger logger = LoggerFactory.getLogger(ZlwGoodsController.class);
        logger.info("goodsMap:{}",iZlwShopGoodsSkuService.getGoodsListByStatus(map).get(ShopsEnum.S_GOODS_ENUM.getValue()));
        return  iZlwShopGoodsSkuService.getGoodsListByStatus(map);
    }

    /**
     * 获取商品首页列表 查询SPU （备）
     * @param data
     * @return
     */
    @PostMapping("/getGoodsSpuListByStatus")
    public Map<String,Page<ZlwShopGoods>> getGoodsSpuListByStatus(@RequestBody Map data){
        Integer sgSpuStatus = (Integer) data.get("sgSpuStatus");
        String shopId = (String) data.get("shopId");
        Long currentPage = Long.valueOf((String) data.get("currentPage"));
        Long sizePage = Long.valueOf((String) data.get("sizePage"));
        Page<ZlwShopGoods> page = new Page<>(currentPage, sizePage);
        Map<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("sgSpuStatus",sgSpuStatus);
        map.put("shopId",shopId);
        Logger logger = LoggerFactory.getLogger(ZlwGoodsController.class);
        logger.info("goodsMap:{}",iZlwShopGoodsService.getGoodsSpuListByStatus(map).get(ShopsEnum.S_GOODS_ENUM.getValue()));
        return  iZlwShopGoodsService.getGoodsSpuListByStatus(map);
    }




    /**
     * 校验自定义商品编码
     * shopId 店铺id
     * customCode 商品编码
     * @return
     */
    @PostMapping("/checkCustomInputCode")
    public ApiResult checkCustomInputCode(@RequestBody Map<String, Object> data){
        ApiResult apiResult;
        try {
            String shopId = (String) data.get("shopId");
            String customCode = (String) data.get("customCode");
            apiResult = iZlwShopGoodsService.checkCustomInputCode(shopId, customCode);
        }catch (Exception e){
            e.printStackTrace();
            apiResult = ApiResult.isErrNoToken("请求失败", null);
        }
        return apiResult;
    }

    /**
     * nayunhao
     * 添加商品，事务控制
     * @param data
     * @return
     */
    @PostMapping("/addGoodsByOne")
    public boolean addGoodsByOne(@RequestBody Map<String,Object> data){
        ZlwShopGoods zlwShopGoods = (ZlwShopGoods)data.get("zlwShopGoods");
        ZlwShopGoodsSku zlwShopGoodsSku = (ZlwShopGoodsSku)data.get("zlwShopGoodsSku");
        ZlwShopGoodsSpec zlwShopGoodsSpec = (ZlwShopGoodsSpec)data.get("zlwShopGoodsSpec");
        ZlwShopGoodsPrice zlwShopGoodsPrice = (ZlwShopGoodsPrice)data.get("zlwShopGoodsPrice");
        ZlwShopGoodsSpecsName zlwShopGoodsSpecsName = (ZlwShopGoodsSpecsName)data.get("zlwShopGoodsSpecsName");
        ZlwShopGoodsSpecsValue zlwShopGoodsSpecsValue = (ZlwShopGoodsSpecsValue)data.get("zlwShopGoodsSpecsValue");
        ZlwShopGoodsImages ZlwShopGoodsImages = (ZlwShopGoodsImages)data.get("ZlwShopGoodsImages");
        ZlwShopGoodsInventory zlwShopGoodsInventory = (ZlwShopGoodsInventory)data.get("zlwShopGoodsInventory");

        try{
            iZlwShopGoodsService.addGoodsTrans(zlwShopGoods,zlwShopGoodsSku,zlwShopGoodsSpec,zlwShopGoodsPrice,zlwShopGoodsSpecsName,zlwShopGoodsSpecsValue,ZlwShopGoodsImages,zlwShopGoodsInventory);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    /**
     * nayunhao
     * 根据分类名查询分类
     * @param className
     * @return
     */
    @PostMapping("/getClassByName")
    public ZlwShopGoodsClass getClassByName(@RequestBody String className){
        ZlwShopGoodsClass zlwShopGoodsClass = iZlwShopGoodsClassService.selectOneByName(className);
        return zlwShopGoodsClass;
    }

}
