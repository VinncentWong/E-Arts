package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.repositories.ArtistRepository;

@Service
public class ArtistService {

	private final ArtistRepository repository;
	
	@Autowired
	public ArtistService(ArtistRepository repository) {
		this.repository = repository;
	}
	
}
