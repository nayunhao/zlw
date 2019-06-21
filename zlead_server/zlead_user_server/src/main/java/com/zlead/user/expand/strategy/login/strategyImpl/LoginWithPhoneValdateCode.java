package com.zlead.user.expand.strategy.login.strategyImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zlead.constant.DataKeysEnum;
import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.entity.user.ZlwUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.zlead.user.expand.strategy.login.LoginStrategy;
import com.zlead.user.invoke.CommonInvoke;
import com.zlead.user.invoke.ZlwUserInvoke;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LoginWithPhoneValdateCode implements LoginStrategy {

    @Override
    public ApiResult login(Map loginInfo) {

        //拿到手机号和短信验证码
        Object userName = loginInfo.get(DataKeysEnum.P_USER_NAME.getValue());
        Object code = loginInfo.get(DataKeysEnum.P_CODE.getValue());

        if(StringUtils.isEmpty(userName))
            return apiResult.setMessage("用户名为空").setStatus(500);
        if(StringUtils.isEmpty(code))
            return apiResult.setMessage("短信验证码为空为空").setStatus(500);

        //验证手机验证码
        String phone = (String) userName;
        String phoneSmsCode = (String) code;
        Map<String,String> map = new ConcurrentHashMap<>();
        map.put(DataKeysEnum.P_PHONE.getValue(),phone);
        map.put(DataKeysEnum.P_CODE.getValue(),phoneSmsCode);
        ApiRequest checkinfo = new ApiRequest();
        checkinfo.setData(map);
        //调用检查验证码的微服务方法
        ApiResult checkSmsCodeRes = commonInvoke.checkSmsCode(checkinfo);
        int commres = checkSmsCodeRes.getStatus();
        //验证失败
        if(commres != 200){
            checkSmsCodeRes.setStatus(500);
            checkSmsCodeRes.setData(gson.fromJson(gson.toJson(new ZlwUser()), new TypeToken<ZlwUser>() {
            }.getType()));
            return checkSmsCodeRes;
        }

        //获取用户信息
        ZlwUser zlwUser = zlwUserInvoke.getUserByPhone(phone);
        if(StringUtils.isEmpty(zlwUser)){
                apiResult.setStatus(500);
                apiResult.setMessage("当前用户未注册");
                apiResult.setData(gson.fromJson(gson.toJson(new ZlwUser()), new TypeToken<ZlwUser>() {
                }.getType()));
                return apiResult;
            }
        //登录成功
        apiResult.setStatus(200);
        apiResult.setMessage("登录成功");
        apiResult.setData(gson.fromJson(gson.toJson(zlwUser), new TypeToken<ZlwUser>() {
            }.getType()));
        return apiResult;

    }

    @Autowired
    private ZlwUserInvoke zlwUserInvoke;

    @Autowired
    private CommonInvoke commonInvoke;

    private static Gson gson = new Gson();
}
