package com.zlead.user.expand.strategy.login.strategyImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zlead.constant.DataKeysEnum;
import com.zlead.constant.PlatformEnum;
import com.zlead.domain.ApiResult;
import com.zlead.domain.VerifyCodeEntity;
import com.zlead.entity.user.ZlwUser;
import com.zlead.user.expand.strategy.login.LoginStrategy;
import com.zlead.user.invoke.CommonInvoke;
import com.zlead.user.invoke.ZlwUserInvoke;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * PC端通过用户名密码和图片验证码登录
 */
@Component
@Slf4j
public class LoginWithImageValdateCode implements LoginStrategy {

    @Override
    public ApiResult login(Map loginInfo) {
        log.info("进入PC端通过用户名密码和图片验证码登录");


        //获取平台
        String platform = (String) loginInfo.get(DataKeysEnum.P_PLATFORM.getValue());
        //获取图片验证码KEY
        String authCodeKey = (String)loginInfo.get(DataKeysEnum.P_AUTH_KEY_CODE.getValue());
        //拿到验证码
        String code = (String)loginInfo.get(DataKeysEnum.P_CODE.getValue());
        //拿到密码
        Object password = loginInfo.get(DataKeysEnum.P_PASSWORD.getValue());
        //拿到手机号
        Object userName = loginInfo.get(DataKeysEnum.P_USER_NAME.getValue());

        if(StringUtils.isEmpty(userName))
            return apiResult.setMessage("用户名为空").setStatus(500);
        if(StringUtils.isEmpty(password))
            return apiResult.setMessage("密码为空").setStatus(500);
        if(!PlatformEnum.网页.getValue().equals(platform))
            return apiResult.setMessage("当前登录方式只支持PC").setStatus(500);

        //校验图片验证码
        VerifyCodeEntity verifyCodeEntity = new VerifyCodeEntity();
        verifyCodeEntity.setAuthCodeKey(authCodeKey);
        verifyCodeEntity.setVerifyCode(code);
        ApiResult checkImageCodeRes = commonInvoke.checkCode(verifyCodeEntity);
        int status = checkImageCodeRes.getStatus();
        if(status != 200){
            //图片验证码验证失败
            checkImageCodeRes.setData(gson.fromJson(gson.toJson(new ZlwUser()), new TypeToken<ZlwUser>() {
            }.getType()));
            checkImageCodeRes.setStatus(300);
            checkImageCodeRes.setMessage("图片验证码验证失败");
            return checkImageCodeRes;
        }
        //根据用户名（这里也就是手机号）获取到用户
        String phone = (String)userName;
        ZlwUser zlwUser = zlwUserInvoke.getUserByPhone(phone);

        //判断是否获取到了用户
        if(StringUtils.isEmpty(zlwUser)){
            //如果没有获取到用户信息，返回用户未注册信息
            apiResult.setStatus(500);
            apiResult.setMessage("当前用户未注册");
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
            apiResult.setStatus(500);
            apiResult.setMessage("密码错误");
            apiResult.setData(gson.fromJson(gson.toJson(new ZlwUser()), new TypeToken<ZlwUser>() {
            }.getType()));
            return apiResult;
        }

        //走到这里说明登录成功
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
