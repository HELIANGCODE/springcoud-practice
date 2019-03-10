package com.heliang.feign.failureCallback;

import org.springframework.stereotype.Component;

import com.heliang.feign.consumer.IHelloService;

@Component
public class Degaradation  implements IHelloService{

	@Override
	public String feignHi(String name) {
		return name+"服务器出错了。。。。";
	}
	
}
