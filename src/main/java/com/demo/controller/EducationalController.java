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
import com.demo.domain.dto.EducationalDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.exception.EducationalNotFoundException;
import com.demo.service.EducationalService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/educational")
@Slf4j
public class EducationalController {
	
	private final EducationalService service;

	@Autowired
	public EducationalController(EducationalService service){
		this.service = service;
	}
	
	@PostMapping("/create/{artistId}")
	public ResponseEntity<Response> createEducational(@RequestBody @Valid EducationalDto dto, 
			@PathVariable("artistId") Long artistId) throws ArtistNotFoundException{
		return this.service.createEducational(dto, artistId);
	}
	
	@GetMapping("/get")
	public ResponseEntity<Response> getEducational
		(@Param("artistId") Long artistId, @Param("educationalId") Long educationalId) throws ArtistNotFoundException{
		log.info("artistId = " + artistId);
		log.info("educational id = " + educationalId);
		return this.service.getEducational(artistId, artistId);
	}
	
	@GetMapping("/gets/{artistId}")
	public ResponseEntity<Response> getEducationals(@PathVariable("artistId") Long artistId) throws ArtistNotFoundException{
		return this.service.getEducationals(artistId);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteEducational
		(@Param("artistId") Long artistId, @Param("educationalId") Long educationalId) throws ArtistNotFoundException, EducationalNotFoundException{
		return this.service.deleteEducational(artistId, educationalId);
	}
	
	@PatchMapping("/update")
	public ResponseEntity<Response> 
		updateEducational(@Param("artistId") Long artistId, 
				@Param("educationalId") Long educationalId, 
				@RequestBody @Valid EducationalDto dto) 
				throws ArtistNotFoundException, EducationalNotFoundException{
		return this.service.updateEducational(artistId, educationalId, dto);
	}
}
