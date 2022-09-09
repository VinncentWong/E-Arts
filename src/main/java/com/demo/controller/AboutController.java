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
import com.demo.domain.dto.AboutDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.service.AboutService;

@RestController
@RequestMapping("/about")
public class AboutController {

	private final AboutService service;
	
	@Autowired
	public AboutController(AboutService service) {
		this.service = service;
	}
	
	@PostMapping("/create/{artistId}")
	public ResponseEntity<Response> createAbout(@PathVariable("artistId") Long artistId, @RequestBody AboutDto dto) throws ArtistNotFoundException{
		return this.service.createAbout(dto, artistId);
	}
	
	@GetMapping("/get/{artistId}")
	public ResponseEntity<Response> getAbout(@PathVariable("artistId") Long artistId) throws ArtistNotFoundException{
		return this.service.getAbout(artistId);
	}
	
	@PatchMapping("/update/{artistId}")
	public ResponseEntity<Response> updateAbout(@PathVariable("artistId") Long artistId, @RequestBody AboutDto dto) throws ArtistNotFoundException{
		return this.service.updateAbout(artistId, dto);
	}
	
	@DeleteMapping("/update/{artistId}")
	public ResponseEntity<Response> deleteAbout(@PathVariable("artistId") Long artistId) throws ArtistNotFoundException{
		return this.service.deleteAbout(artistId);
	}
}
