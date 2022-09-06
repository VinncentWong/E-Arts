package com.demo.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil<T> {
	
	public String generateToken(IJwt<T> jwt, T obj) {
		return jwt.generateToken(obj);
	}
	
	public String implementationGenerateToken(T obj) {
		Map<String, Object> claims = new HashMap<>();
		return "";
	}
}
