package com.zlead.controller;



import com.zlead.base.BaseController;
import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.ZlwShopGoods;
import com.zlead.entity.goods.ZlwShopGoodsClass;
import com.zlead.service.IZlwShopGoodsClassService;
import com.zlead.service.IZlwShopGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName 刘祎航
 * @Description TODO
 * @Author Administrator
 * @Date 2019/6/513:55
 * @Version 1.0
 **/
@RestController
@RequestMapping("/goods")
public class ZlwShopGoodsLController extends BaseController {

    @Autowired
    private IZlwShopGoodsClassService zlwShopGoodsClassService;
    @Autowired
    private IZlwShopGoodsService  iZlwShopGoodsService;



    /**
     * 查询店铺分类
     * @param apiRequest
     * @return
     */
    @PostMapping("/getShopClassLeve1")
    public ApiResult getShopClassLeve1(@RequestBody ApiRequest apiRequest){
        Map<String,Object>map=new HashMap<>();
                List<ZlwShopGoodsClass> shopClass= new  ArrayList<>();
                String sgcParentId=null;
                String shopId=null;
                try{
                    shopId=(String.valueOf(apiRequest.getData().get("shopId")));
                    sgcParentId=(String.valueOf(apiRequest.getData().get("sgcParentId")));
                    if(shopId!=null&&!shopId.equals("")&&sgcParentId!=null&&!sgcParentId.equals("")){
                        shopClass = zlwShopGoodsClassService.selectListAll(sgcParentId,shopId);
                        map.put("shopClass",shopClass);
                        return ApiResult.isOkNoToken("成功",map);
                    }else {
                        return ApiResult.isOkNoToken("shopId，sgcParentId，必传",map);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    return ApiResult.isErrNoToken("系统错误",map);
                }
    }

    @PostMapping("/ShopGoods/{shopId}")
    public List<ZlwShopGoods> selectSpuList(@PathVariable("shopId") String shopId){
        Integer sgIsDelete=2;
        return iZlwShopGoodsService.selectSpuList(shopId,sgIsDelete);
    }

    @PostMapping("/getShopClassTree")
    public ApiResult getShopClassTree(){
        Map<String,Object>map=new HashMap<>();
        try{
            List<ZlwShopGoodsClass> zlwShopGoodsClasses = zlwShopGoodsClassService.selectListAllTree();
            map.put("shopClass",zlwShopGoodsClasses);
            return ApiResult.isOkNoToken("成功",map);
        }catch (Exception e){
            return ApiResult.isErrMessage("服务调用失败");
        }
    }















}
