package com.demo.controller;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
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
import com.demo.domain.dto.ArtworkDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.exception.ArtworkNotFoundException;
import com.demo.service.ArtworkService;

@RestController
@RequestMapping("/artwork")
public class ArtWorkController {
	
	private final ArtworkService service;
	
	@Autowired
	public ArtWorkController(ArtworkService service) {
		this.service = service;
	}
	
	@PostMapping("/create/{artistId}")
	public ResponseEntity<Response> createArtwork(@RequestBody @Valid ArtworkDto dto, @PathVariable("artistId") Long id)
			throws ArtistNotFoundException{
		return this.service.createArtwork(dto, id);
	}
	
	@GetMapping("/gets/{artistId}")
	public ResponseEntity<Response> getArtworks(@PathVariable Long id) throws ArtworkNotFoundException{
		return this.service.getArtwork(id);
	}
	
	@PatchMapping("/update")
	public ResponseEntity<Response> updateArtwork(@Param("artistId") Long artistId, @Param("artworkId") Long artworkId, @RequestBody @Valid ArtworkDto dto) 
			throws ArtworkNotFoundException{
		return this.service.updateArtwork(artworkId, artistId, dto);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteArtwork(@Param("artistId") Long artistId, @Param("artworkId") Long artworkId)
			throws ArtworkNotFoundException, ArtistNotFoundException{
		return this.service.deleteArtwork(artworkId, artistId);
	}
}

