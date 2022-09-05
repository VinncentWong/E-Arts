package com.demo.domain;

import lombok.Data;

@Data
public class Response {

	private String message;
	
	private boolean success;
	
	private Object data;
}
