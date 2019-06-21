package com.zlead.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlead.base.BaseController;
import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.entity.cart.ZlwOrderShoppingCartData;
import com.zlead.entity.goods.ZlwPlatformGoods;
import com.zlead.entity.goods.ZlwShopGoods;
import com.zlead.entity.goods.ZsgZpg;
import com.zlead.invoke.ShopGoodsInvoke;
import com.zlead.service.IZlwPlatformGoodsService;
import com.zlead.service.ZsgZgpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName 刘祎航
 * @Description TODO
 * @Author Administrator
 * @Date 2019/6/513:55
 * @Version 1.0
 **/
@RestController
@RequestMapping("/ZlwGoods")
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class ZlwShopPlatformLController extends BaseController {

    @Autowired
    private IZlwPlatformGoodsService iZlwPlatformGoodsService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ShopGoodsInvoke shopGoodsInvoke;
    @Autowired
    private ZsgZgpService zsgZgpService;





    /**
     * 查询平台商品列表（分页）
     * @param apiRequest
     * @return
     */
    @PostMapping("/getPlatformGoodsList")
    public ApiResult getPlatformGoodsList(@RequestBody ApiRequest apiRequest) {
        Map<String, Object> map = new HashMap<>();
        try {
            Integer currentPage =Integer.valueOf(String.valueOf(apiRequest.getData().get("currentPage")));
            Integer sizePage = Integer.valueOf(String.valueOf(apiRequest.getData().get("sizePage")));
            String shopId = String.valueOf(apiRequest.getData().get("shopId"));
            List<ZlwShopGoods> zlwShopGoods = shopGoodsInvoke.selectSpuList(shopId);
            if(zlwShopGoods.size()>0){
               // List<String> collect = zlwShopGoods.stream().map(c -> c.getSpuCode()).collect(Collectors.toList());
                //创建临时表
                zsgZgpService.createTableZlw();
                boolean b = zsgZgpService.insertZsgZpgList(zlwShopGoods);
                if(b){
                    Long aPage = Long.valueOf(currentPage);
                    Long aSize = Long.valueOf(sizePage);
                    Page<ZlwPlatformGoods> page = new Page<>(aPage,aSize);
                        Page<ZlwPlatformGoods>  zlwPlatformGoodsPage = page.setRecords(iZlwPlatformGoodsService.selectpageExists(page));
                        if(zlwPlatformGoodsPage!=null){
                            map.put("goods",zlwPlatformGoodsPage);
                            //删除临时表
                            zsgZgpService.delTableZlw();
                        }
                        return ApiResult.isOkNoToken("成功",map);
                }
            }
            Long aPage = Long.valueOf(currentPage);
            Long aSize = Long.valueOf(sizePage);
            Page<ZlwPlatformGoods> page = new Page<>(aPage,aSize);
            Page<ZlwPlatformGoods>  zlwPlatformGoodsPage = page.setRecords(iZlwPlatformGoodsService.selectpage(page));
            map.put("goods",zlwPlatformGoodsPage);
            return ApiResult.isOkNoToken("成功",map);
        } catch (Exception e) {
            System.out.println(e.toString()+"错误");
            return ApiResult.isErrNoToken("系统错误", map);
        }
    }


    @PostMapping("/addCart")
    public ApiResult cartTest(@RequestBody ApiRequest apiRequest){
        List<ZlwOrderShoppingCartData> list=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        ZlwOrderShoppingCartData zlwOrderShoppingCartData=new ZlwOrderShoppingCartData();
        String userId = String.valueOf(apiRequest.getData().get("userId"));
        String oscdId = String.valueOf(apiRequest.getData().get("oscdId"));
        String sgId = String.valueOf(apiRequest.getData().get("sguId"));
        String osciId = String.valueOf(apiRequest.getData().get("osciId"));
        zlwOrderShoppingCartData.setOscdId(oscdId);
        zlwOrderShoppingCartData.setSgId(sgId);
        zlwOrderShoppingCartData.setOsciId(osciId);
        zlwOrderShoppingCartData.setOcsdCount(1);
            SessionCallback sessionCallback=new SessionCallback() {
                @Override
                @Transactional
                public List execute(RedisOperations operations) throws DataAccessException {
                    redisTemplate.setEnableTransactionSupport(true);
                    BoundHashOperations boundHashOperations = operations.boundHashOps(userId);
                    boundHashOperations.put(oscdId,zlwOrderShoppingCartData);
                    operations.multi();
                    return operations.exec();
                }
            };
            redisTemplate.execute(sessionCallback);

        return ApiResult.isOkNoToken("成功",map);
    }


    @GetMapping("delTable")
    public  void  delTable(){
        zsgZgpService.delTableZlw();
}

}
