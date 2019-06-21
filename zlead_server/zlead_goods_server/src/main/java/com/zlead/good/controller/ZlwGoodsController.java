package com.zlead.good.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.zlead.base.BaseController;
import com.zlead.constant.ShopsEnum;
import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.entity.goods.ZlwShopGoods;
import com.zlead.entity.goods.ZlwShopGoodsClass;
import com.zlead.entity.goods.ZlwShopGoodsSku;
import com.zlead.good.invoke.CommonInvoke;
import com.zlead.good.invoke.ZlwGoodsInvoke;
import com.zlead.utils.JsonMapper;
import com.zlead.utils.StringUtil;
import com.zlead.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * goods controller
 */

@RestController
@RequestMapping("/ZlwGoods")
@Slf4j
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class ZlwGoodsController extends BaseController {

    @Autowired
    private CommonInvoke commonInvoke;
    @Autowired
    private ZlwGoodsInvoke zlwGoodsInvoke;


    /* *
     * @Author guoyunfei
     * @Description //TODO 批量更新店铺商品状态
     * @Date 下午 3:25 2019/6/5 0005
     * @Param [apiRequest]
     * @return com.zlead.domain.ApiResult
     */
    @PostMapping("/batchEditGoodsStatus")
    public ApiResult editGoodsStatus(@RequestBody ApiRequest apiRequest) {
        Map<String, Boolean> param = new HashMap<String, Boolean>();
        Map data = apiRequest.getData();
        String goodsIds = (String) data.get("sgGoodsIds");
        String type = (String) data.get("sgType");
        if (StringUtils.isEmpty(goodsIds) || StringUtils.isEmpty(type)) {
            param.put("isSuccess", false);
            return ApiResult.isErrNoToken("缺少必填参数", param);
        }
        int isSuccess = zlwGoodsInvoke.editGoodsStatus(data);
        param.put("isSuccess", false);
        if (isSuccess == 1) {
            return ApiResult.isErrNoToken("该商品库存为零,不能上架!", param);
        }else if (isSuccess == 2){
            return ApiResult.isErrNoToken("该商品未添加库存!", param);
        }else if (isSuccess == 3){
            return ApiResult.isErrNoToken("没有该类型操作!", param);
        }else if (isSuccess == 4){
            return ApiResult.isErrNoToken("更新异常", param);
        }
        param.put("isSuccess", true);
        return ApiResult.isOkNoToken("更新成功", param);
    }

    /* *
     * @Author GuoYunFei
     * @Description //TODO 批量设置店铺商品分类
     * @Date 下午 3:35 2019/6/5 0005
     * @Param [apiRequest]
     * @return com.zlead.domain.ApiResult
     */
    @PostMapping("/batchSetGoodsClass")
    public ApiResult setGoodsClass(@RequestBody ApiRequest apiRequest) {
        ApiResult result = new ApiResult();
        Map<String, Boolean> param = new HashMap<String, Boolean>();
        Map data = apiRequest.getData();
        String goodsIds = (String) data.get("sgGoodsIds");
        Integer sgClassId1 = (Integer) data.get("sgClassId1");
        if (StringUtils.isEmpty(goodsIds) || null == sgClassId1) {
            result.setStatus(ApiResult.CODE_ERR);
            result.setMessage("缺少必填参数!");
            param.put("isSuccess", false);
            result.setData(param);
            return result;
        }
        boolean isSuccess = zlwGoodsInvoke.setBatchGoodsClass(data);
        if (!isSuccess) {
            param.put("isSuccess", false);
            return ApiResult.isErrNoToken("设置分类失败", param);
        }
        param.put("isSuccess", true);
        return ApiResult.isOkNoToken("设置成功", param);
    }

    /* *
     * @Author GuoYunFei
     * @Description //TODO 修改店铺商品分类
     * @Date 下午 6:38 2019/6/5 0005
     * @Param [apiRequest]
     * @return com.zlead.domain.ApiResult
     */
    @RequestMapping(value = "/editShopGoodsClass", method = RequestMethod.POST)
    public ApiResult editShopGoodsClass(@RequestBody ApiRequest apiRequest) {
        ApiResult result = new ApiResult();
        Map<String, Boolean> param = new HashMap<String, Boolean>();
        Map data = apiRequest.getData();
        String sgcId = (String) data.get("sgcId");
        if (StringUtils.isEmpty(sgcId)) {
            result.setStatus(ApiResult.CODE_ERR);
            result.setMessage("缺少必填参数!");
            param.put("isSuccess", false);
            result.setData(param);
            return result;
        }
        int isSuccess = zlwGoodsInvoke.editShopGoodsClass(data);
        if(2 == isSuccess){
            param.put("isSuccess",false);
            return ApiResult.isErrNoToken("修改店铺分类失败",param);
        }else if(1 == isSuccess){
            param.put("isSuccess",false);
            return ApiResult.isErrNoToken("分类名称重复",param);
        }
        param.put("isSuccess", true);
        return ApiResult.isOkNoToken("修改成功", param);
    }


    /* *
     * @Author GuoYunFei
     * @Description //TODO 删除店铺商品分类
     * @Date 下午 6:45 2019/6/5 0005
     * @Param
     * @return
     */
    @RequestMapping(value = "/removeShopGoodsClass", method = RequestMethod.POST)
    public ApiResult removeShopGoodsClass(@RequestBody ApiRequest apiRequest) {
        ApiResult result = new ApiResult();
        Map<String, Boolean> param = new HashMap<String, Boolean>();
        Map data = apiRequest.getData();
        String sgcId = (String) data.get("sgcId");
        if (StringUtils.isEmpty(sgcId)) {
            result.setStatus(ApiResult.CODE_ERR);
            result.setMessage("缺少必填参数!");
            param.put("isSuccess", false);
            result.setData(param);
            return result;
        }
        try {
            boolean isSuccess = zlwGoodsInvoke.removeShopGoodsClass(sgcId);
            if (!isSuccess) {
                param.put("isSuccess", false);
                return ApiResult.isErrNoToken("删除店铺分类失败", param);
            }
        } catch (Exception e) {
            e.printStackTrace();
            param.put("isSuccess", false);
            return ApiResult.isErrNoToken("删除店铺分类失败", param);
        }
        param.put("isSuccess", true);
        return ApiResult.isOkNoToken("删除成功", param);
    }


    /**
     * 首页展示
     * @param apiRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/getGoodsListByStatus")
    public ApiResult getGoodsListByStatus(@RequestBody ApiRequest apiRequest) throws Exception {

        ApiResult apiResult = new ApiResult();
        Map data = apiRequest.getData();
        log.info("传入参数:{}",new Gson().toJson(data));
        Map<String,Page<ZlwShopGoodsSku>> pageMap = zlwGoodsInvoke.getGoodsListByStatus(data);
        if(pageMap == null||pageMap.isEmpty()){
            apiResult.setStatus(ApiResult.CODE_ERR);
            apiResult.setMessage("该店铺信息为空");
            return apiResult;
        }
        //pageMap返回结果信息
        log.info("pageMap:{}" + new Gson().toJson(pageMap));

        if (pageMap.get(ShopsEnum.S_GOODS_ENUM.getValue()).getRecords() == null || pageMap.get(ShopsEnum.S_GOODS_ENUM.getValue()).getRecords().size() < 1 ) {
            apiResult.setStatus(ApiResult.CODE_ERR);
            apiResult.setMessage("没有查到店铺信息");
            return apiResult;
        }
        apiResult.setMessage("查询店铺信息成功");
        apiResult.setData(pageMap.get(ShopsEnum.S_GOODS_ENUM.getValue()));
        return apiResult;
    }

    /**
     * 获取商品列表 SPU
     * @param apiRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/getGoodsSpuListByStatus")
    public ApiResult getGoodsSpuListByStatus(@RequestBody ApiRequest apiRequest) throws Exception {

        ApiResult apiResult = new ApiResult();
        Map data = apiRequest.getData();
//        logger.info("传入参数:{}",data.toString());
        Map<String,Page<ZlwShopGoods>> pageMap = zlwGoodsInvoke.getGoodsSpuListByStatus(data);
//        logger.info("pageMap:{}" + pageMap);
        if(pageMap == null||pageMap.isEmpty()){
            apiResult.setStatus(ApiResult.CODE_ERR);
            apiResult.setMessage("该店铺信息为空");
            return apiResult;
        }

        if (pageMap.get(ShopsEnum.S_GOODS_ENUM.getValue()).getRecords() == null || pageMap.get(ShopsEnum.S_GOODS_ENUM.getValue()).getRecords().size() < 1 ) {
            apiResult.setStatus(ApiResult.CODE_ERR);
            apiResult.setMessage("查到店铺信息失败");
            return apiResult;
        }
        apiResult.setMessage("查询店铺信息成功");
        apiResult.setData(pageMap.get(ShopsEnum.S_GOODS_ENUM.getValue()));
        return apiResult;
    }

    /**
     * nayunhao
     * 添加分类
     * @param apiRequest
     * @return
     */
    @RequestMapping(value = "/addShopGoodsClass", method = RequestMethod.POST)
    public ApiResult addShopGoodsClass(@RequestBody ApiRequest apiRequest) {
        Map data = apiRequest.getData();
        String shopId = (String) data.get("shopId");
        String className = (String) data.get("className");
        String parentId = (String) data.get("parentId");
        String sgcId = commonInvoke.getGoodsId();

        Map errorData = new HashMap();
        Map successData = new HashMap();
        errorData.put("isSuccess", false);
        successData.put("isSuccess", true);
        if (StringUtil.isNull(shopId) || StringUtil.isNull(className) || StringUtil.isNull(parentId)) {
            return ApiResult.isErrNoToken("必填参数为空或未填", errorData);
        }
        if (sgcId == null || "0000".equals(sgcId)) {
            log.error("生成分类id失败");
            return ApiResult.isErrNoToken("id生成有误", errorData);
        }

        ZlwShopGoodsClass zlwShopGoodsClass = new ZlwShopGoodsClass();
        zlwShopGoodsClass.setSgcId(sgcId);
        zlwShopGoodsClass.setShopId(shopId);
        zlwShopGoodsClass.setSgcName(className);
        zlwShopGoodsClass.setSgcParentId(parentId);
        if ("0".equals(parentId)) {
            zlwShopGoodsClass.setSgcLevel(1);
        } else {
            zlwShopGoodsClass.setSgcLevel(2);
        }
        boolean result = zlwGoodsInvoke.addShopGoodsClass(zlwShopGoodsClass);
        if (result) {
            return ApiResult.isOkNoToken("添加成功", successData);
        } else {
            return ApiResult.isErrNoToken("添加失败", errorData);
        }
    }

    /**
     * 单条添加商品
     * @param apiRequest
     * @return
     */
    @RequestMapping(value="/addGoodsByOne",method = RequestMethod.POST)
    public ApiResult addGoodsByOne(@RequestBody ApiRequest apiRequest){
        Map data =  apiRequest.getData();
        String shopId = (String) data.get("shopid");
        String sgStatus = (String)data.get("goodStatus");
        String userid = (String)data.get("userid");
        String goodsDetail = (String)data.get("goodsDetail");
        JsonMapper jsonMapper = JsonMapper.getInstance();
        Map goodsDetailData = jsonMapper.fromJson(goodsDetail,Map.class);
        List<Map> goodsSpDataArray = jsonMapper.fromJson((String)goodsDetailData.get("goodsSpDetail"),List.class);
        return null;
    }

    /* *
     * @Author xuguangwei
     * @Description //TODO 校验自定义商品编码
     * @Date 下午 6:38 2019/6/5 0005
     * @Param [apiRequest]
     * @return com.zlead.domain.ApiResult
     */
    @RequestMapping(value = "/checkCustomInputCode", method = RequestMethod.POST)
    public ApiResult checkCustomInputCode(@RequestBody ApiRequest apiRequest) {
        Map data = apiRequest.getData();

        String shopId = (String) data.get("shopId");
        String customCode = (String) data.get("customCode");
        if(StringUtils.isEmpty(shopId)){
            return ApiResult.isErrNoToken("店铺参数为空", null);
        }
        if(StringUtils.isEmpty(customCode)){
            return ApiResult.isErrNoToken("商品编码参数为空", null);
        }

        return zlwGoodsInvoke.checkCustomInputCode(data);
    }

}
