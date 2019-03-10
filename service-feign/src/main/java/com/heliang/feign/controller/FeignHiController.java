package com.heliang.feign.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heliang.feign.consumer.IHelloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class FeignHiController {
	
	private static Logger log = LoggerFactory.getLogger(FeignHiController.class);
	
	@Autowired
	private IHelloService helloService;
	
	@Value("${server.port}")
    String port;
	
	@RequestMapping("hello")
	public String feignHello(@RequestParam(value="name")String name){
		log.info("调用feign。。。。");
		return  helloService.feignHi(name);
	}
	
	@RequestMapping("hello1")
	@HystrixCommand(fallbackMethod = "hiError")
	public String feignHello1(@RequestParam(value="name")String name){
		log.info("调用feign。。。。");
		return  helloService.feignHi(name);
	}
	
	public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }
}
