package com.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Response;
import com.demo.domain.artist.Artist;
import com.demo.domain.dto.BiographyDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.repositories.ArtistRepository;
import com.demo.util.ResponseUtil;

@Service
@Transactional
public class BiographyService {
	
	private final ArtistRepository repository;
	
	private final ResponseUtil util;
	
	@Autowired
	public BiographyService(ArtistRepository repository, ResponseUtil util) {
		this.repository = repository;
		this.util = util;
	}
	
	public ResponseEntity<Response> createBiography(Long artistId, BiographyDto dto) throws ArtistNotFoundException{
		Artist artist = this.repository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		artist.setBiography(dto.getBiography());
		this.repository.updateArtistBiography(dto.getBiography(), artistId);
		return util.sendCreated("sukses membuat biography", true, dto.getBiography());
	}
	
	public ResponseEntity<Response> getBiography(Long artistId) throws ArtistNotFoundException{
		Artist artist = this.repository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		return util.sendOk("sukses mendapatkan data biography", true, artist.getBiography());
	}
	
	public ResponseEntity<Response> updateBiography(Long artistId, BiographyDto dto) throws ArtistNotFoundException{
		this.repository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		this.repository.updateArtistBiography(dto.getBiography(), artistId);
		return util.sendOk("sukses mengupdate data biography", true, null);
	}
	
	public ResponseEntity<Response> deleteBiography(Long artistId) throws ArtistNotFoundException{
		this.repository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		this.repository.updateArtistBiography(null, artistId);
		return util.sendOk("sukses menghapus data biography", true, null);
	}
}
