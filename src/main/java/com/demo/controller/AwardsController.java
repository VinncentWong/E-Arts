package com.demo.controller;

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
import com.demo.domain.dto.AwardsDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.exception.AwardsNotFoundException;
import com.demo.service.AwardsService;

import net.bytebuddy.asm.Advice.This;

@RestController
@RequestMapping("/awards")
public class AwardsController {

	private final AwardsService service;
	
	@Autowired
	public AwardsController(AwardsService service) {
		this.service = service;
	}
	
	@PostMapping("/create/{artistId}")
	public ResponseEntity<Response> createAwards(@RequestBody AwardsDto dto, @PathVariable("artistId") Long artistId) 
			throws ArtistNotFoundException{
		return this.service.createAwards(dto, artistId);
	}
	
	@GetMapping("/get/{artistId}")
	public ResponseEntity<Response> getAwards(@PathVariable("artistId") Long artistId)
			throws ArtistNotFoundException, AwardsNotFoundException{
		return this.service.getAwards(artistId);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteAwards(@Param("artistId") Long artistId, @Param("awardsId") Long awardsId)
			throws ArtistNotFoundException, AwardsNotFoundException{
		return this.service.deleteAwards(awardsId, artistId);
	}
	
	@PatchMapping("/update")
	public ResponseEntity<Response> updateAwards
	(@RequestBody AwardsDto dto, @Param("artistId") Long artistId, @Param("awardsId") Long awardsId){
		return this.updateAwards(dto, artistId, awardsId);
	}
}
