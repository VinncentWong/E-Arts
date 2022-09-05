package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Artist;
import com.demo.domain.Response;
import com.demo.domain.dto.SignUpDto;
import com.demo.repositories.ArtistRepository;

@Service
public class ArtistService {

	private final ArtistRepository repository;
	
	@Autowired
	public ArtistService(ArtistRepository repository) {
		this.repository = repository;
	}
}
