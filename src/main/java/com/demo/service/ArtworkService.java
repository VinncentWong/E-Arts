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
import com.demo.util.ResponseUtil;

@Service
@Transactional
public class ArtworkService {

	private final ArtworkRepository repository;
	
	private final ArtistRepository artistRepository;
	
	private final ResponseUtil util;
	
	@Autowired
	public ArtworkService(ArtworkRepository repository, ArtistRepository artistRepository, ResponseUtil util) {
		this.repository = repository;
		this.artistRepository = artistRepository;
		this.util = util;
	}
	
	public ResponseEntity<Response> createArtwork(ArtworkDto dto, Long artistId) throws ArtistNotFoundException{
		Artist artist = this.artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		ArtWork artwork = new ArtWork();
		artist.setArtwork(artwork);
		Artist data = this.artistRepository.save(artist);
		return this.util.sendCreated("sukses membuat artwork!", true, data);
	}
}
