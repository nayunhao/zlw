package com.zlead.user.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zlead.base.BaseController;
import com.zlead.constant.DataKeysEnum;
import com.zlead.constant.LoginMessageEnum;
import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.entity.user.ZlwUser;
import com.zlead.user.expand.strategy.login.LoginStrategy;
import com.zlead.user.expand.strategy.login.strategyImpl.LoginFactory;
import com.zlead.user.invoke.CommonInvoke;
import com.zlead.user.invoke.ZlwUserInvoke;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ZlwUser")
@Slf4j
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class ZlwUserConller extends BaseController {

    @PostMapping("/login")
    public ApiResult login(@RequestBody ApiRequest apiRequest){

        ApiResult apiResult = new ApiResult();
        //因为后面还校验了平台，这里为了图省事直接把平台放到data里面
        apiRequest.getData().put(DataKeysEnum.P_PLATFORM.getValue(),apiRequest.getPlatform());

        //获取到登录逻辑的实例
        LoginStrategy loginStrategy = LoginFactory.getLoginFactoryInstance()
                .getLoginStrategy(apiRequest.getData());

        if(StringUtils.isEmpty(loginStrategy)){

            apiResult.setMessage("没有符合条件的登录方式");
            apiResult.setStatus(500);
            return apiResult;
        }

        apiResult = loginStrategy.login(apiRequest.getData());
        return apiResult;

    }


    @PostMapping(value = "/isRegByPhone")
    public ApiResult getUserByPhone(@RequestBody ApiRequest apiRequest){

        //这个接口不需要校验token

        Map data = apiRequest.getData();
        String phone = (String) data.get(DataKeysEnum.P_PASSWORD.getValue());
        log.info("电话号码：{}",phone);
        Map<String,Boolean> map = new HashMap<>();
        ZlwUser zlwUser = zlwUserInvoke.getUserByPhone(phone);
        ApiResult apiResult = new ApiResult();
        if(StringUtils.isEmpty(zlwUser)){
            apiResult.setMessage(LoginMessageEnum.L_NOT_REG.getMessage());
            map.put("isSuccess",true);

        }else {
            apiResult.setMessage(LoginMessageEnum.L_ALREADY_REG.getMessage());
            apiResult.setStatus(LoginMessageEnum.L_ALREADY_REG.getCode());
            map.put("isSuccess", false);

        }
        apiResult.setData(map);
        apiResult.setStatus(200);
        return apiResult;
    }

    @PostMapping("/setPassword")
    public ApiResult setPassWord(@RequestBody ApiRequest apiRequest){
        Map data = apiRequest.getData();
        Map<String,Object> map = new HashMap<>();
        ApiResult apiResult = new ApiResult();
        //校验用户是否注册
        String phone = (String) data.get(DataKeysEnum.P_PHONE.getValue());
        ZlwUser zlwUser = zlwUserInvoke.getUserByPhone(phone);
        if(!StringUtils.isEmpty(zlwUser)){
            map.put("isSuccess",false);
            map.put(DataKeysEnum.P_USER_ID.getValue(),"");
            apiResult.setStatus(LoginMessageEnum.L_ALREADY_REG.getCode());
            apiResult.setMessage(LoginMessageEnum.L_ALREADY_REG.getMessage());
            apiResult.setData(map);
            return apiResult;
        }

        //生成UserId
        String userId = commonInvoke.getUserId();
        data.put(DataKeysEnum.P_USER_ID.getValue(),userId);

        log.info("传入参数:{}",data.toString());
        boolean createUserRes = zlwUserInvoke.createUser(data);

        if(createUserRes){
            map.put("isSuccess",true);
            map.put(DataKeysEnum.P_USER_ID.getValue(),createUserRes);
            apiResult.setMessage(LoginMessageEnum.L_ADDUSER_OK.getMessage());
        }else{
            map.put("isSuccess",false);
            map.put(DataKeysEnum.P_USER_ID.getValue(),"");
            apiResult.setStatus(LoginMessageEnum.L_ADDUSER_ERR.getCode());
            apiResult.setMessage(LoginMessageEnum.L_ADDUSER_ERR.getMessage());
        }
        apiResult.setData(map);
        return apiResult;
    }

    @PostMapping("/createShop")
    public ApiResult createShop(@RequestBody ApiRequest apiRequest){
        Map data = apiRequest.getData();
        Map<String,Object> datares = new HashMap<>();
        String companyId = commonInvoke.getCompanyId();
        data.put(DataKeysEnum.P_COMPANY_ID.getValue(),companyId);
        ApiResult apiResult = new ApiResult();

            boolean shopRes = zlwUserInvoke.addShop(data);
            if(shopRes){
                apiResult.setStatus(LoginMessageEnum.L_ADDSHOP_OK.getCode());
                apiResult.setMessage(LoginMessageEnum.L_ADDSHOP_OK.getMessage());
                datares.put("isSeccuess",true);
                apiResult.setData(datares);
            }else{
                apiResult.setStatus(LoginMessageEnum.L_ADDSHOP_ERR.getCode());
                apiResult.setMessage(LoginMessageEnum.L_ADDSHOP_ERR.getMessage());
                datares.put("isSeccuess",false);
                apiResult.setData(datares);
            }

            return apiResult;

    }

    @PostMapping("/getShopsByUserId")
    public ApiResult getShopsByUserId(@RequestBody ApiRequest apiRequest) throws Exception {

        Map data = apiRequest.getData();
        String userId = (String)data.get(DataKeysEnum.P_USER_ID.getValue());
        ZlwUser zlwUser = zlwUserInvoke.getShopByUser(userId);
        ApiResult apiResult = new ApiResult();
        if(zlwUser == null || zlwUser.getZlwCompanyList().size() < 1){
            apiResult.setMessage(LoginMessageEnum.L_NOSHOP_INFO.getMessage());
            apiResult.setData(gson.fromJson(gson.toJson(zlwUser), new TypeToken<ZlwUser>() {
            }.getType()));
            apiResult.setStatus(LoginMessageEnum.L_NOSHOP_INFO.getCode());
            return apiResult;
        }
        apiResult.setMessage(LoginMessageEnum.L_SHOP_INFO.getMessage());
        apiResult.setData(gson.fromJson(gson.toJson(zlwUser), new TypeToken<ZlwUser>() {
        }.getType()));
        return apiResult;
    }

    @PostMapping("/resetPassword")
    public ApiResult resetPassword(@RequestBody ApiRequest apiRequest){
        ApiResult apiResult = new ApiResult();
        Map data = apiRequest.getData();

        //拿到手机号
        String phone = (String) data.get(DataKeysEnum.P_PHONE.getValue());

        //调用检查验证码的微服务方法
        ApiResult microRes = commonInvoke.checkSmsCode(apiRequest);
        int responseCode = microRes.getStatus();
        if(responseCode!=200){
            return microRes;
        }

        ZlwUser zlwUser = zlwUserInvoke.getUserByPhone(phone);

        if(StringUtils.isEmpty(zlwUser)){
            Map map = new HashMap();
            map.put("isSeccuess",false);
            apiResult.setStatus(LoginMessageEnum.L_NOT_REG.getCode());
            apiResult.setMessage(LoginMessageEnum.L_NOT_REG.getMessage());
        }

        //设置密码
        String newpassword = (String)data.get(DataKeysEnum.P_PASSWORD.getValue());
        String userId = (String)data.get(DataKeysEnum.P_USER_ID.getValue());
        Map<String,String> map = new HashMap<>();
        map.put(DataKeysEnum.P_PASSWORD.getValue(),newpassword);
        map.put(DataKeysEnum.P_USER_ID.getValue(),userId);
        boolean res = zlwUserInvoke.setPassWord(data);
        Map<String,Boolean> reSetRes = new HashMap<>();
        String message = res ? LoginMessageEnum.L_CHANGE_PASS_OK.getMessage() : LoginMessageEnum.L_CHANGE_PASS_ERR.getMessage();
        int status = res ? LoginMessageEnum.L_CHANGE_PASS_OK.getCode() : LoginMessageEnum.L_CHANGE_PASS_ERR.getCode();
        reSetRes.put("isSeccuess",res);
        apiResult.setMessage(message);
        apiResult.setData(reSetRes);
        return apiResult;
    }


    @PostMapping("/checkShopIsExist")
    public ApiResult checkShopIsExist(@RequestBody ApiRequest apiRequest){

        ApiResult apiResult = new ApiResult();
        Map data = apiRequest.getData();

        String shopName = (String) data.get(DataKeysEnum.P_SHOPNAME);

        boolean res = zlwUserInvoke.getShopByName(shopName);

        if(res){
            apiResult.setMessage(LoginMessageEnum.L_SHOP_ALREADY_REG.getMessage());
            apiResult.setStatus(LoginMessageEnum.L_SHOP_NOTYET_REG.getCode());
        }else{
            apiResult.setMessage(LoginMessageEnum.L_SHOP_NOTYET_REG.getMessage());
            apiResult.setStatus(LoginMessageEnum.L_SHOP_NOTYET_REG.getCode());
        }
        Map resdata = new HashMap();
        resdata.put("isExist",res);
        apiResult.setData(resdata);

        return apiResult;
    }

    @Autowired
    private ZlwUserInvoke zlwUserInvoke;

    @Autowired
    private CommonInvoke commonInvoke;

    private static Gson gson = new Gson();

}
