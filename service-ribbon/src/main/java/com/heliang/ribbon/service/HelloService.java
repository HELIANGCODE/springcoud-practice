package com.heliang.ribbon.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HelloService {
	
	private static Logger log = LoggerFactory.getLogger(HelloService.class);
	
	@Autowired
	RestTemplate restTemplate;

	
	@HystrixCommand(fallbackMethod = "hiError")
	public String hiService(String name) {
		log.info("调用ribbon。。。");
		return restTemplate.getForObject("http://EURAKA-CLIENT/helloEurakaClient?name=" + name, String.class);
	}

	public String hiError(String name){
		return "hi ribbon 调用服务异常！,name："+name+"";
	}
}
