package com.zlead.eureka;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {
//注册中心eureka
//eureka_dmy 1
	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);
	}

}
