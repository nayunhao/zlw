package com.zlead;

import com.zlead.utils.SpringApplicationContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class UserServerApplication {
    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(UserServerApplication.class, args);
        SpringApplicationContextUtil.setApplicationContext(applicationContext);
    }
}
