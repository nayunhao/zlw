package com.zlead.zuul.filter;

import com.google.gson.Gson;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zlead.constant.DataKeysEnum;
import com.zlead.constant.LoginMessageEnum;
import com.zlead.domain.ApiResult;
import com.zlead.domain.JWTEntity;
import com.zlead.utils.JWTUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.util.StreamUtils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;

/**
 * 登录拦截器
 */
public class LoginFilter extends ZuulFilter {

    @Value("${zlead.loginStr}")
    private String loginMethod;

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {

        RequestContext context = getCurrentContext();
        String method = context.getRequest().getRequestURI();

        //如果是登录方法就进入此拦截器中
        return StringUtils.equals(method,loginMethod);
    }

    @Override
    public Object run() throws ZuulException {
        try {
        //生成token
        RequestContext context = getCurrentContext();

            InputStream in = context.getResponseDataStream();
            String responseBody = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
            Gson gson = new Gson();
            ApiResult apiResult = gson.fromJson(responseBody, ApiResult.class);

            //判断登录是否成功
            if(LoginMessageEnum.L_LOGIN_OK.getCode() != apiResult.getStatus()){
                return null;
            }
            //获得userId
            Map zlwUser = (Map) apiResult.getData();
            String userId = (String) zlwUser.get(DataKeysEnum.P_USER_ID.getValue());
            //生成JWT
            String jwtToken = JWTUtils.createJWT(userId,"token", JWTEntity.JWT_TTL);
            apiResult.setToken(jwtToken);

            //返回给前端
            context.setResponseBody(gson.toJson(apiResult));


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
