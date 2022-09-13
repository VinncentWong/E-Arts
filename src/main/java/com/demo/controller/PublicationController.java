package com.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.Response;
import com.demo.domain.dto.PublicationDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.exception.PublicationNotFoundException;
import com.demo.service.PublicationsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/publication")
@Slf4j
public class PublicationController {

	private final PublicationsService service;
	
	@Autowired
	public PublicationController(PublicationsService service) {
		this.service = service;
	}
	
	@PostMapping("/create/{artistId}")
	public ResponseEntity<Response> createPublication(@RequestBody @Valid PublicationDto dto, @PathVariable("artistId") Long artistId)
		throws ArtistNotFoundException{
		log.info("dto = " + dto.getPublication());
		return this.service.createPublication(dto, artistId);
	}
	
	@GetMapping("/gets/{artistId}")
	public ResponseEntity<Response> getPublications(@PathVariable("artistId") Long id) 
			throws PublicationNotFoundException{
		return this.service.getPublications(id);
	}
	
	@GetMapping("/get")
	public ResponseEntity<Response> getPublication(@Param("artistId") Long artistId, @Param("publicationId") Long publicationId) 
			throws PublicationNotFoundException, ArtistNotFoundException{
		return this.service.getPublication(artistId, publicationId);
	}
	
	@PatchMapping("/update")
	public ResponseEntity<Response> updatePublication(@Param("artistId") Long artistId, 
			@Param("publicationId") Long publicationId, @RequestBody @Valid PublicationDto dto) 
			throws ArtistNotFoundException, PublicationNotFoundException
		{
		return this.service.updatePublication(artistId, publicationId, dto);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Response> deletePublication(@Param("artistId") Long artistId, 
			@Param("publicationId") Long publicationId) 
			throws ArtistNotFoundException, PublicationNotFoundException{
		return this.service.deletePublication(artistId, publicationId);
	}
}
