/**
 * Copyright (c) 2019 LightFish Inc
 * Created by sunquan on 2019-04-19.
 */
package com.zlead.platform.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ConfigBean {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}