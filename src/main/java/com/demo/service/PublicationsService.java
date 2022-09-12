package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Response;
import com.demo.repositories.PublicationRepository;

@Service
public class PublicationsService {

	private final PublicationRepository repository;
	
	@Autowired
	public PublicationsService(PublicationRepository repository) {
		this.repository = repository;
	}
	
	public ResponseEntity<Response> createPublication(){
		return null;
	}
}
