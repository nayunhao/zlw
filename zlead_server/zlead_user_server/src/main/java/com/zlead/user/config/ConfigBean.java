/**
 * Copyright (c) 2019 LightFish Inc
 * Created by sunquan on 2019-04-19.
 */
package com.zlead.user.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *配置类
 *@author sunquan
 *@create 2019-04-19
 *@Email 2581557729@qq.com
 */

@Configuration
public class ConfigBean {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}