package com.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.ArtWork;
import com.demo.domain.Artist;
import com.demo.domain.Response;
import com.demo.domain.dto.ArtworkDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.repositories.ArtistRepository;
import com.demo.repositories.ArtworkRepository;

@Service
@Transactional
public class ArtworkService {

	private final ArtworkRepository repository;
	
	private final ArtistRepository artistRepository;
	
	@Autowired
	public ArtworkService(ArtworkRepository repository, ArtistRepository artistRepository) {
		this.repository = repository;
		this.artistRepository = artistRepository;
	}
	
	public ResponseEntity<Response> createArtwork(ArtworkDto dto, Long artistId) throws ArtistNotFoundException{
		Artist artist = this.artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		ArtWork artwork = new ArtWork();
		return null;
	}
}
