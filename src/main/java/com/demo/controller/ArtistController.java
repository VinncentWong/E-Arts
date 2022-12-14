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
import com.demo.domain.dto.AfterLoginDto;
import com.demo.domain.dto.ExpertiseDto;
import com.demo.domain.dto.LoginDto;
import com.demo.domain.dto.SignUpDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.exception.ExpertiseNotFoundException;
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

	@PostMapping("/adddata/{id}")
	public ResponseEntity<Response> addArtistData(@RequestBody @Valid AfterLoginDto dto, @PathVariable("id") Long id) throws ArtistNotFoundException{
		return service.addArtistData(dto, id);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Response> getArtistById(@PathVariable("id") Long id) throws ArtistNotFoundException{
		return service.getArtistById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteArtist(@PathVariable("id") Long id) throws ArtistNotFoundException{
		return service.deleteArtist(id);
	}
	
	@PostMapping("/addexpertise/{id}")
	public ResponseEntity<Response> addArtistExpertise(@PathVariable Long id, @RequestBody @Valid ExpertiseDto dto) 
			throws ArtistNotFoundException, ExpertiseNotFoundException{
		return service.addArtistExpertise(id, dto);
	}

	@GetMapping("/getexpertise/{id}")
	public ResponseEntity<Response> getArtistExpertise(@PathVariable Long id) 
			throws ArtistNotFoundException, ExpertiseNotFoundException{
		return service.getArtistExpertise(id);
	}
	
	@DeleteMapping("/deleteexpertise")
	public ResponseEntity<Response> deleteArtistExpertise(@Param("idArtist") Long idArtist, @Param("idExpertise") Long idExpertise)
			throws NullPointerException, ExpertiseNotFoundException, ArtistNotFoundException{
		try {
			return service.deleteArtistExpertise(idArtist, idExpertise);
		}
		catch(NullPointerException ex) {
			throw new ExpertiseNotFoundException();
		}
	}
	
	@PatchMapping("/updateexpertise")
	public ResponseEntity<Response> updateArtistExpertise
			(@Param("idArtist") Long idArtist, @Param("idExpertise") Long idExpertise, @RequestBody @Valid ExpertiseDto dto) 
			throws ArtistNotFoundException, ExpertiseNotFoundException{
		return service.updateArtistExpertise(idExpertise, idArtist, dto);
	}
}
