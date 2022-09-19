package com.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.domain.Response;
import com.demo.domain.dto.ArtworkDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.exception.ArtworkNotFoundException;
import com.demo.service.ArtworkService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/artwork")
@Slf4j
public class ArtWorkController {
	
	private final ArtworkService service;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	public ArtWorkController(ArtworkService service) {
		this.service = service;
	}
	
	@PostMapping(path = "/create/{artistId}")
	public ResponseEntity<Response> createArtwork(@RequestPart("artwork") @Valid ArtworkDto dto, @PathVariable("artistId") Long id, @RequestPart("photo") List<MultipartFile> file)
			throws ArtistNotFoundException, IOException{
		log.info("request = " + request.getRequestURI());
		log.info(dto.toString());
		log.info(file.toString());
		return this.service.createArtwork(dto, id, file);
	}
	
	@GetMapping("/gets/{artistId}")
	public ResponseEntity<Response> getArtworks(@PathVariable("artistId") Long id) throws ArtworkNotFoundException{
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

