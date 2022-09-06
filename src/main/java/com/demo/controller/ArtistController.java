package com.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.Response;
import com.demo.domain.dto.ExpertiseDto;
import com.demo.domain.dto.LoginDto;
import com.demo.domain.dto.SignUpDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.exception.InternalServerErrorException;
import com.demo.service.ArtistService;

@RestController
@RequestMapping("/artist")
public class ArtistController {
	
	private final ArtistService service;
	
	@Autowired
	public ArtistController(ArtistService service) {
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<Response> createArtist(@RequestBody @Valid SignUpDto dto){
		return service.createArtist(dto);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Response> loginArtist(@RequestBody @Valid LoginDto dto) throws ArtistNotFoundException, InternalServerErrorException{
		return service.loginArtist(dto);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Response> getArtistById(@PathVariable Long id) throws ArtistNotFoundException{
		return service.getArtistById(id);
	}
	
	@PostMapping("/addexpertise/{id}")
	public ResponseEntity<Response> addArtistExpertise(@PathVariable Long id, @RequestBody ExpertiseDto dto) throws ArtistNotFoundException{
		return service.addArtistExpertise(id, dto);
	}

}
