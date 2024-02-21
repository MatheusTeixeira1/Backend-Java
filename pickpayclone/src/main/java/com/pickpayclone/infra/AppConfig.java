package com.pickpayclone.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	@Bean
	public RestTemplate RestTemplate() {
		return new RestTemplate();
		//53:30
	}
}
