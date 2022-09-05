package com.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.Response;
import com.demo.domain.dto.SignUpDto;
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
	public ResponseEntity<Response> createUser(@RequestBody @Valid SignUpDto dto){
		return service.createArtist(dto);
	}
}
