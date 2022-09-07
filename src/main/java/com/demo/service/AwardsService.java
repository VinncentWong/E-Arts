package com.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Artist;
import com.demo.domain.Response;
import com.demo.domain.dto.AwardsDto;
import com.demo.repositories.ArtistRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AwardsService {
	
	private final ArtistRepository artistRepo;
	
	@Autowired
	public AwardsService(ArtistRepository artistRepo) {
		this.artistRepo = artistRepo;
	}

	public ResponseEntity<Response> createAwards(AwardsDto dto, Long artistId){
		Artist artist = artistRepo.findById(artistId).orElseThrow();
		return null;
	}
	
}
