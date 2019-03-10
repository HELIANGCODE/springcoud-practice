package com.heliang.eurakaclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurakaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run( EurakaClientApplication.class, args );
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("/helloEurakaClient")
    public String helloEurakaClientt(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }

}
