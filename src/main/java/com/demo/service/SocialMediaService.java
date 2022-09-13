package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Response;
import com.demo.domain.dto.SocialMediaDto;
import com.demo.repositories.SocialMediaRepository;

@Service
public class SocialMediaService {

	private SocialMediaRepository repository;
	
	@Autowired
	public SocialMediaService(SocialMediaRepository repository) {
		this.repository = repository;
	}
	
	public ResponseEntity<Response> createSocialMedia(SocialMediaDto dto, Long artistId){
		return null;
	}
}
