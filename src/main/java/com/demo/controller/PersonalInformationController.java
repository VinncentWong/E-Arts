package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.demo.domain.dto.PersonalInformationDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.exception.PersonalInformationNotFoundException;
import com.demo.service.PersonalInformationService;

@RestController
@RequestMapping("/personalinf")
public class PersonalInformationController {

	private final PersonalInformationService service;
	
	@Autowired
	public PersonalInformationController(PersonalInformationService service) {
		this.service = service;
	}
	
	@PostMapping("/create/{artistId}")
	public ResponseEntity<Response> createPersonalInformation(@PathVariable("artistId") Long artistId, @RequestBody PersonalInformationDto dto)
			throws ArtistNotFoundException{
		return this.service.createPersonalInformation(dto, artistId);
	}
	
	@GetMapping("/get/{artistId}")
	public ResponseEntity<Response> getPersonalInformation(@PathVariable("artistId") Long artistId) 
			throws PersonalInformationNotFoundException{
		return this.service.getPersonalInformation(artistId);
	}
	
	@PatchMapping("/update/{artistId}")
	public ResponseEntity<Response> updatePersonalInformation(@PathVariable("artistId") Long artistId, @RequestBody PersonalInformationDto dto)
			throws ArtistNotFoundException, PersonalInformationNotFoundException{
		return this.service.updatePersonalInformation(dto, artistId);
	}
	
	@DeleteMapping("/delete/{artistId}")
	public ResponseEntity<Response> deletePersonalInformation(@PathVariable("artistId") Long artistId) 
			throws ArtistNotFoundException, PersonalInformationNotFoundException{
		return this.service.removePersonalInformation(artistId);
	}
}
