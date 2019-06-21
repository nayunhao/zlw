package com.zlead.user.expand.strategy.login.strategyImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zlead.constant.DataKeysEnum;
import com.zlead.constant.LoginMessageEnum;
import com.zlead.constant.PlatformEnum;
import com.zlead.domain.ApiResult;
import com.zlead.entity.user.ZlwUser;
import com.zlead.user.expand.strategy.login.LoginStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.zlead.user.invoke.ZlwUserInvoke;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
/**
 * 仅使用用户名和密码登录
 */
public class LoginWithoutCode implements LoginStrategy {

    @Override
    public ApiResult login(Map loginInfo) {
        log.info("进入仅使用用户名和密码登录");


        //获取用户名和密码
        Object userName = loginInfo.get(DataKeysEnum.P_USER_NAME.getValue());
        Object password = loginInfo.get(DataKeysEnum.P_PASSWORD.getValue());

        //平台也要拿到
        String platform = (String) loginInfo.get(DataKeysEnum.P_PLATFORM.getValue());
        //判断是否为空
        if(StringUtils.isEmpty(userName))
            return apiResult.setMessage("用户名为空").setStatus(500);
        if(StringUtils.isEmpty(password))
            return apiResult.setMessage("密码为空").setStatus(500);
        if(PlatformEnum.网页.getValue().equals(platform))
            return apiResult.setMessage("当前登录方式只支持app").setStatus(500);

        //根据用户名（这里也就是手机号）获取到用户
        String phone = (String)userName;
        ZlwUser zlwUser = zlwUserInvoke.getUserByPhone(phone);

        //判断是否获取到了用户
        if(StringUtils.isEmpty(zlwUser)){
            //如果没有获取到用户信息，返回用户未注册信息
            apiResult.setStatus(LoginMessageEnum.L_NOT_REG.getCode());
            apiResult.setMessage(LoginMessageEnum.L_NOT_REG.getMessage());
            apiResult.setData(gson.fromJson(gson.toJson(new ZlwUser()), new TypeToken<ZlwUser>() {
            }.getType()));
            return apiResult;
        }

        //获取到用户后，进行密码校验
        Map<String,String> checkPasswordMap = new ConcurrentHashMap<>();
        checkPasswordMap.put(DataKeysEnum.P_PASSWORD.getValue(),(String)password);
        checkPasswordMap.put(DataKeysEnum.P_PHONE.getValue(),phone);
        boolean codeIsRight = zlwUserInvoke.checkPassword(checkPasswordMap);
        if(!codeIsRight){
            //密码不正确
            apiResult.setStatus(LoginMessageEnum.L_PASSWORD_ERR.getCode());
            apiResult.setMessage(LoginMessageEnum.L_PASSWORD_ERR.getMessage());
            apiResult.setData(gson.fromJson(gson.toJson(new ZlwUser()), new TypeToken<ZlwUser>() {
            }.getType()));
            return apiResult;
        }

        //走到这里说明登录成功
        apiResult.setStatus(LoginMessageEnum.L_LOGIN_OK.getCode());
        apiResult.setMessage(LoginMessageEnum.L_LOGIN_OK.getMessage());
        apiResult.setData(gson.fromJson(gson.toJson(zlwUser), new TypeToken<ZlwUser>() {
        }.getType()));
        return apiResult;
    }

    @Autowired
    private ZlwUserInvoke zlwUserInvoke;

    private static Gson gson = new Gson();
}
