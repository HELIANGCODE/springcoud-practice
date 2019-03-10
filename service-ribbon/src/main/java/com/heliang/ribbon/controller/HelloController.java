package com.heliang.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heliang.ribbon.service.HelloService;

@RestController
public class HelloController {
	
	@Autowired
	HelloService helloService;

	@GetMapping(value = "/hello")
	public String helloRibbon(@RequestParam String name) {
		return helloService.hiService(name);
	}
}
