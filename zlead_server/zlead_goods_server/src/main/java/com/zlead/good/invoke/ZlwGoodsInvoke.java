package com.zlead.good.invoke;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.*;
import com.zlead.good.invoke.callback.ZlwGoodsFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(name = "zlead-goods-service", fallback = ZlwGoodsFallback.class)
public interface ZlwGoodsInvoke {

    @PostMapping("/ZlwGoods/getGoodsListByStatus")
    public Map<String, Page<ZlwShopGoodsSku>> getGoodsListByStatus(@RequestBody Map data);

    /**
     * 添加分类
     * @param zlwShopGoodsClass
     * @return
     */
    @PostMapping("/ZlwGoods/addShopGoodsClass")
    public boolean addShopGoodsClass(@RequestBody ZlwShopGoodsClass zlwShopGoodsClass);

    /**
     * 添加单个商品
     * @param data
     * @return
     */
    @PostMapping("/ZlwGoods/addGoodsByOne")
    public boolean addGoodsByOne(@RequestBody Map<String,Object> data);
    /**
     * 根据分类名称查询分类
     * @param className
     * @return
     */
    @PostMapping("/ZlwGoods/getClassByName")
    public ZlwShopGoodsClass getClassByName(@RequestBody String className);
    @PostMapping("/ZlwGoods/importGoods")
    public ApiResult importGoods(@RequestBody ApiRequest apiRequest);
    @PostMapping("/ZlwGoods/addShopClass")
    public boolean addShopClass(@RequestBody ZlwShopGoodsClass zlwShopGoodsClass);

    @PostMapping("/ZlwGoods/editGoodsStatus")
    int editGoodsStatus(@RequestBody Map map);

    @PostMapping("/ZlwGoods/batchSetGoodsClass")
    boolean setBatchGoodsClass(@RequestBody Map map);

    @PostMapping("/ZlwGoods/editShopGoodsClass")
    int editShopGoodsClass(@RequestBody Map data);

    @PostMapping("/ZlwGoods/removeShopGoodsClass/{sgcId}")
    boolean removeShopGoodsClass(@PathVariable("sgcId") String sgcId);

    @PostMapping("/ZlwGoods/getGoodsBrand")
    Map<String, Object> getGoodsBrand(@RequestBody Map<String, Object> data);

    @PostMapping("/ZlwGoods/getShopGoodsUnit")
    Map<String, List<ZlwShopGoodsUnit>> getShopGoodsUnit();

    @PostMapping("/ZlwGoods/checkCustomInputCode")
    ApiResult checkCustomInputCode(@RequestBody Map<String, Object> data);
    @PostMapping("/ZlwGoods/getGoodsSpuListByStatus")
    Map<String, Page<ZlwShopGoods>> getGoodsSpuListByStatus(Map data);
}
