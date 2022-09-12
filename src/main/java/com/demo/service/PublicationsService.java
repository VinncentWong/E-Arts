package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Artist;
import com.demo.domain.Publications;
import com.demo.domain.Response;
import com.demo.domain.dto.PublicationDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.repositories.ArtistRepository;
import com.demo.repositories.PublicationRepository;
import com.demo.util.ResponseUtil;

@Service
public class PublicationsService {

	private final PublicationRepository repository;
	
	private final ArtistRepository artistRepository;
	
	private final ResponseUtil util;
	
	@Autowired
	public PublicationsService(PublicationRepository repository, ArtistRepository artistRepository, ResponseUtil util) {
		this.repository = repository;
		this.artistRepository = artistRepository;
		this.util = util;
	}
	
	public ResponseEntity<Response> createPublication(PublicationDto dto, Long artistId) throws ArtistNotFoundException{
		Artist artist = this.artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		Publications publication = new Publications();
		publication.setPublications(dto.getPublication());
		publication.setArtist(artist);
		this.repository.save(publication);
		return util.sendCreated("sukses membuat publication", true, artist);
	}
}
