package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Response;
import com.demo.domain.dto.BiographyDto;
import com.demo.repositories.ArtistRepository;

@Service
public class BiographyService {
	
	private final ArtistRepository repository;
	
	@Autowired
	public BiographyService(ArtistRepository repository) {
		this.repository = repository;
	}
	
	public ResponseEntity<Response> createBiography(Long artistId, BiographyDto dto){
		return null;
	}
}
