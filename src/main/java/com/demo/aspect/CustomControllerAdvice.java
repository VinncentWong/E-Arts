package com.demo.aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.domain.Response;
import com.demo.exception.ArtistNotFoundException;
import com.demo.exception.ExpertiseNotFoundException;
import com.demo.exception.InternalServerErrorException;
import com.demo.util.ResponseUtil;

@RestControllerAdvice
public class CustomControllerAdvice {
	
	private final ResponseUtil util;
	
	@Autowired
	CustomControllerAdvice(ResponseUtil util){
		this.util = util;
	}
	
	@ExceptionHandler(ArtistNotFoundException.class)
	public ResponseEntity<Response> sendArtistNotFoundException(){
		return util.sendInternalServerError("data artist tidak ditemukan di database", false);
	}
	
	@ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<Response> sendInternalServerErrorException(){
		return util.sendInternalServerError("terjadi kesalahan di dalam server", false);
	}
	
	@ExceptionHandler(ExpertiseNotFoundException.class)
	public ResponseEntity<Response> sendExpertiseNotFoundException(){
		return util.sendInternalServerError("data expertise tidak ditemukan", false);
	}
}
