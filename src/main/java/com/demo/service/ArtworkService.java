package com.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Response;
import com.demo.repositories.ArtworkRepository;

@Service
@Transactional
public class ArtworkService {

	private final ArtworkRepository repository;
	
	@Autowired
	public ArtworkService(ArtworkRepository repository) {
		this.repository = repository;
	}
	
	public ResponseEntity<Response> createArtwork(){
		return null;
	}
}
