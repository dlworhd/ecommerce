package com.hexagonal.payment.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomConfig {
	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
