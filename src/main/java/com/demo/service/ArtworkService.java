package com.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.ArtWork;
import com.demo.domain.Artist;
import com.demo.domain.Response;
import com.demo.domain.dto.ArtworkDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.exception.ArtworkNotFoundException;
import com.demo.repositories.ArtistRepository;
import com.demo.repositories.ArtworkRepository;
import com.demo.util.ResponseUtil;
import com.demo.util.SetterNullAware;

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
	
	public ResponseEntity<Response> getArtwork(Long artistId) throws ArtworkNotFoundException{
		List<ArtWork> listArtwork = this.repository.getArtworks(artistId).orElseThrow(() -> new ArtworkNotFoundException());
		return this.util.sendOk("sukses mendapatkan artwork", true, listArtwork);
	}
	
	public ResponseEntity<Response> updateArtwork(Long artworkId, Long artistId, ArtworkDto dto) throws ArtworkNotFoundException{
		ArtWork artwork = this.repository.getArtwork(artistId, artworkId).orElseThrow(() -> new ArtworkNotFoundException());
		SetterNullAware setter = new SetterNullAware();
		setter.setString(artwork::setName, artwork.getName());
		setter.setString(artwork::setDescription, dto.getDescription());
		setter.setNumber(artwork::setStock, dto.getStock());
		setter.setBoolean(artwork::setStatus, dto.getStatus());
		setter.setCategory(artwork::setCategory, dto.getCategory());
		setter.setByte(artwork::setPhoto, dto.getPhoto());
		
	}
}
