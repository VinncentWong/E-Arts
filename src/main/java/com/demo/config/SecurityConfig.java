package com.demo.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain security(HttpSecurity http) throws Exception{
		http.csrf().disable()
		.cors((c) -> {
			CorsConfigurationSource cors = (request) -> {
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedHeaders(List.of("*"));
				config.setAllowedMethods(List.of("*"));
				config.setAllowedOrigins(List.of("*"));
				return config;
			};
			c.configurationSource(cors);
		})
		.formLogin().disable()
		.httpBasic().disable();
		return http.build();
	}
}
