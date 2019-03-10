package com.heliang.feign.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.heliang.feign.failureCallback.Degaradation;

@FeignClient(value="euraka-client",fallback=Degaradation.class)
public interface IHelloService {
	
	@RequestMapping(value="helloEurakaClient")
	public String feignHi(@RequestParam(value="name")String name); 
}
