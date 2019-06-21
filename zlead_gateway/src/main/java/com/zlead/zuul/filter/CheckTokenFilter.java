package com.zlead.zuul.filter;

import com.google.gson.Gson;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zlead.constant.SystemMessageEnum;
import com.zlead.domain.ApiRequest;
import com.zlead.domain.ApiResult;
import com.zlead.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import static com.netflix.zuul.context.RequestContext.getCurrentContext;

/**
 * 验证token的拦截器
 */
public class CheckTokenFilter extends ZuulFilter {

    @Value("${zlead.releaseMethods}")
    private List<String> releaseMethods;

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {

        RequestContext context = getCurrentContext();
        String method = context.getRequest().getRequestURI();

        return !releaseMethods.contains(method);
    }

    @Override
    public Object run() throws ZuulException {

        try {
            RequestContext context = getCurrentContext();

            HttpServletRequest httpServletRequest = context.getRequest();

            String method = httpServletRequest.getRequestURI();

            System.out.println("当前请求的方法是："+method);

            InputStream in = httpServletRequest.getInputStream();
            String requestBody = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
            if(StringUtils.isEmpty(requestBody)){
                   return null;

            }
            Gson gson = new Gson();
            ApiRequest apiRequest = gson.fromJson(requestBody,ApiRequest.class);
            String token = apiRequest.getToken();

            /**
             * 判断下请求的接口，如果是登录接口，在登录成功后要返回一个token，
             * 注册，或者获取验证码的接口不需要验证token
             * 其他接口都需要验证token
             */

            //验证token
            boolean tokenIsValue = JWTUtils.checkToken(apiRequest);
            if(tokenIsValue){
                System.out.println("token校验成功!");
                return null;
            }

            //失败返回参数
            ApiResult apiResult = new ApiResult();
            apiResult.setStatus(SystemMessageEnum.TOKEN_INVALID.getCode());
            apiResult.setMessage(SystemMessageEnum.TOKEN_INVALID.getMessage());
            apiResult.setData("");
            apiResult.setToken("");
            context.setResponseBody(gson.toJson(apiResult));

            //中断转发
            context.setSendZuulResponse(false);
            //context.setResponseStatusCode(401);
            context.getResponse().setContentType("text/html;charset=UTF-8");
            return null;


        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
