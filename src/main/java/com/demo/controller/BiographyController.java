package com.demo.controller;

import javax.validation.Valid;

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
import com.demo.domain.dto.BiographyDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.service.BiographyService;

@RestController
@RequestMapping("/biography")
public class BiographyController {

	private final BiographyService service;
	
	@Autowired
	public BiographyController(BiographyService service) {
		this.service = service;
	}
	
	@PostMapping("/create/{artistId}")
	public ResponseEntity<Response> createBiography(@RequestBody @Valid BiographyDto dto, @PathVariable("artistId") Long id) throws ArtistNotFoundException{
		return this.service.createBiography(id, dto);
	}
	
	@GetMapping("/get/{artistId}")
	public ResponseEntity<Response> getBiography(@PathVariable("artistId") Long artistId) throws ArtistNotFoundException{
		return this.service.getBiography(artistId);
	}
	
	@PatchMapping("/update/{artistId}")
	public ResponseEntity<Response> updateBiography(@PathVariable("artistId") Long artistId, @RequestBody @Valid BiographyDto dto)
				throws ArtistNotFoundException{
		return this.service.updateBiography(artistId, dto);
	}
	
	@DeleteMapping("/delete/{artistId}")
	public ResponseEntity<Response> deleteBiography(@PathVariable("artistId") Long artistId) throws ArtistNotFoundException{
		return this.service.deleteBiography(artistId);
	}
	
	
}
