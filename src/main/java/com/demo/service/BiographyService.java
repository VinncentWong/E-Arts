package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Artist;
import com.demo.domain.Response;
import com.demo.domain.dto.BiographyDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.repositories.ArtistRepository;
import com.demo.util.ResponseUtil;

@Service
public class BiographyService {
	
	private final ArtistRepository repository;
	
	private final ResponseUtil util;
	@Autowired
	public BiographyService(ArtistRepository repository, ResponseUtil util) {
		this.repository = repository;
		this.util = util;
	}
	
	public ResponseEntity<Response> createBiography(Long artistId, BiographyDto dto) throws ArtistNotFoundException{
		this.repository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		Artist artist = this.repository.createArtistBiography(dto.getBiography(), artistId).get();
		return util.sendCreated("sukses membuat biography", true, artist);
	}
	
	public ResponseEntity<Response> getBiography(Long artistId) throws ArtistNotFoundException{
		Artist artist = this.repository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		return util.sendOk("sukses mendapatkan data biography", true, artist.getBiography());
	}
}
