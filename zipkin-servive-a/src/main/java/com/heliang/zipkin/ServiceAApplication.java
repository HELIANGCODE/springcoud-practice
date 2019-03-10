package com.heliang.zipkin;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import brave.sampler.Sampler;

@SpringBootApplication
@RestController
public class ServiceAApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceAApplication.class, args);
	}

	private static final Logger LOG = Logger.getLogger(ServiceAApplication.class.getName());

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@RequestMapping("/hi")
	public String callHome() {
		LOG.log(Level.INFO, "calling trace zipkin-servive-a  ");
		return restTemplate.getForObject("http://localhost:8989/miya", String.class);
	}

	@RequestMapping("/info")
	public String info() {
		LOG.log(Level.INFO, "calling trace zipkin-servive-a ");
		return "i'm zipkin-servive-a";

	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
