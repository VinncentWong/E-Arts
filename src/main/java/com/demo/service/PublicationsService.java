package com.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Response;
import com.demo.domain.artist.Artist;
import com.demo.domain.artist.Publications;
import com.demo.domain.dto.PublicationDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.exception.PublicationNotFoundException;
import com.demo.repositories.ArtistRepository;
import com.demo.repositories.PublicationRepository;
import com.demo.util.ResponseUtil;

@Service
@Transactional
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
	
	public ResponseEntity<Response> getPublications(Long id) throws PublicationNotFoundException{
		List<Publications> listPublications = this.repository.getPublications(id).orElseThrow(() -> new PublicationNotFoundException());
		return util.sendOk("success menemukan data publications", true, listPublications);
	}
	
	public ResponseEntity<Response> getPublication(Long artistId, Long publicationId) 
			throws PublicationNotFoundException, ArtistNotFoundException{
		this.artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		Publications publication = this.repository.getPublication(artistId, publicationId).orElseThrow(() -> new PublicationNotFoundException());
		return util.sendOk("sukses menemukan data publication", false, publication);
	}
	
	public ResponseEntity<Response> updatePublication(Long artistId, Long publicationId, PublicationDto dto)
		throws ArtistNotFoundException, PublicationNotFoundException{
		this.artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		this.repository.getPublication(artistId, publicationId).orElseThrow(() -> new PublicationNotFoundException());
		this.repository.updatePublication(artistId, publicationId, dto.getPublication());
		return util.sendOk("data publication sukses diupdate", true, null);
	}
	
	public ResponseEntity<Response> deletePublication(Long artistId, Long publicationId)
			throws ArtistNotFoundException, PublicationNotFoundException{
		this.artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		this.repository.getPublication(artistId, publicationId).orElseThrow(() -> new PublicationNotFoundException());
		this.repository.deletePublication(artistId, publicationId);
		return util.sendOk("data publication sukses dihapus", true, null);
	}
}
