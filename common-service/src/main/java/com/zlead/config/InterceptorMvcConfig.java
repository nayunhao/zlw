package com.zlead.config;

import com.zlead.interceptor.MoreRefreshInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorMvcConfig implements WebMvcConfigurer {

    @Autowired
    private MoreRefreshInterceptor moreRefreshInterceptor;

    /**
     * 添加拦截器控制频繁刷新
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(moreRefreshInterceptor).addPathPatterns("/GenerateCode");
    }

}
