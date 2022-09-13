package com.demo.controller;

import javax.validation.Valid;

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
import com.demo.domain.dto.SocialMediaDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.exception.SocialMediaNotFoundException;
import com.demo.service.SocialMediaService;

@RestController
@RequestMapping("/socialmedia")
public class SocialMediaController {

	private final SocialMediaService service;
	
	public SocialMediaController(SocialMediaService service) {
		this.service = service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Response> createSocialMedia(@PathVariable("artistId") Long artistId, @RequestBody @Valid SocialMediaDto dto) 
			throws ArtistNotFoundException {
		return this.service.createSocialMedia(dto, artistId);
	}
	
	@GetMapping("/gets/{artistId}")
	public ResponseEntity<Response> getSocialMedias(@PathVariable("artistId") Long artistId) 
			throws SocialMediaNotFoundException{
		return this.service.getSocialMedias(artistId);
	}
	
	@GetMapping("/get")
	public ResponseEntity<Response> getSocialMedia(@Param("artistId") Long artistId, @Param("socialMediaId") Long socialMediaId) 
			throws SocialMediaNotFoundException{
		return this.service.getSocialMedia(artistId, socialMediaId);
	}
	
	@PatchMapping("/update")
	public ResponseEntity<Response> updateSocialMedia
		(@Param("artistId") Long artistId, @Param("socialMediaId") Long socialMediaId, @RequestBody @Valid SocialMediaDto dto) 
				throws SocialMediaNotFoundException, ArtistNotFoundException{
		return this.service.updateSocialMedia(artistId, socialMediaId, dto);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteSocialMedia(@Param("artistId") Long artistId, @Param("socialMediaId") Long socialMediaId)
			throws SocialMediaNotFoundException, ArtistNotFoundException{
		return this.service.deleteSocialMedia(artistId, socialMediaId);
	}
	
}
