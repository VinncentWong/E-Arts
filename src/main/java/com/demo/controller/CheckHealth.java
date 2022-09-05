package com.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckHealth {

	@GetMapping("/checkhealth")
	public Map<String, Object> checkHealth() {
		var map = new HashMap<String, Object>();
		map.put("message", "OK");
		return map;
	}
}
