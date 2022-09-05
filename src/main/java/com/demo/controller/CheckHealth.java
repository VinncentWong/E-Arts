package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckHealth {

	@GetMapping("/checkhealth")
	public String checkHealth() {
		return "OK";
	}
}
