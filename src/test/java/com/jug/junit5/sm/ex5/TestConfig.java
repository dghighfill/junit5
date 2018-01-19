package com.jug.junit5.sm.ex5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

	@Bean
	Ingredient ingredient() {
		return new Ingredient();
	}
}
