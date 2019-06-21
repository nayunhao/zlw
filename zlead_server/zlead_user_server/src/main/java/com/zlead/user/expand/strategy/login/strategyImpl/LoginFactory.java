package com.zlead.user.expand.strategy.login.strategyImpl;

import com.zlead.constant.DataKeysEnum;
import com.zlead.utils.SpringApplicationContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import com.zlead.user.expand.strategy.login.LoginStrategy;

import java.util.Map;

public class LoginFactory {

    private LoginStrategy loginStrategy;

    private LoginFactory() {
    }

    public static LoginFactory getLoginFactoryInstance(){

        return new LoginFactory();
    }

    //根据参数的不同，实例化不同的实现类
    public LoginStrategy getLoginStrategy(Map loginInfo){

        //获取图片验证码KEY
        Object authCodeKey = loginInfo.get(DataKeysEnum.P_AUTH_KEY_CODE.getValue());
        //拿到验证码
        Object code = loginInfo.get(DataKeysEnum.P_CODE.getValue());
        //拿到密码
        Object password = loginInfo.get(DataKeysEnum.P_PASSWORD.getValue());
        //拿到手机号
        Object userName = loginInfo.get(DataKeysEnum.P_USER_NAME.getValue());


        boolean passwordIsEmpty = StringUtils.isEmpty(password);
        boolean codeIsEmpty = StringUtils.isEmpty(code);
        boolean authCodeKeyIsEmpty = StringUtils.isEmpty(authCodeKey);

        if(codeIsEmpty && authCodeKeyIsEmpty){
            //只有用户名密码的登录策略
            return this.loginStrategy = SpringApplicationContextUtil.getBean(LoginWithoutCode.class);
        }
        if(passwordIsEmpty && authCodeKeyIsEmpty){
            //短信验证码的登录
            return this.loginStrategy = SpringApplicationContextUtil.getBean(LoginWithPhoneValdateCode.class);
        }
        if((!authCodeKeyIsEmpty) && (!codeIsEmpty) && (!passwordIsEmpty)){
            //图片验证码的登录
            return this.loginStrategy = SpringApplicationContextUtil.getBean(LoginWithImageValdateCode.class);
        }

        return null;
    }

}
