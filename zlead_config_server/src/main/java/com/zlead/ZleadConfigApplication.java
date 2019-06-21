package com.zlead;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author guoyunfei
 * @date 2019/6/10 0010 下午 6:00
 */

@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class ZleadConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZleadConfigApplication.class, args);
    }

}
