package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.domain.Artist;
import com.demo.domain.PersonalInformation;
import com.demo.domain.Response;
import com.demo.domain.dto.LoginDto;
import com.demo.domain.dto.SignUpDto;
import com.demo.repositories.ArtistRepository;
import com.demo.repositories.PersonalInformationRepository;
import com.demo.util.ResponseUtil;

@Service
public class ArtistService {

	private final PersonalInformationRepository repository;
	
	private final BCryptPasswordEncoder bcrypt;
	
	private final ResponseUtil util;
	
	private final ArtistRepository artistRepo;
	@Autowired
	public ArtistService(PersonalInformationRepository repository, BCryptPasswordEncoder bcrypt, ResponseUtil util, ArtistRepository artistRepo) {
		this.repository = repository;
		this.bcrypt = bcrypt;
		this.util = util;
		this.artistRepo = artistRepo;
	}
	
	public ResponseEntity<Response> createArtist(SignUpDto dto){
		Artist artist = new Artist();
		artist.setEmail(dto.getEmail());
		artist.setPassword(bcrypt.encode(dto.getPassword()));
		PersonalInformation tempPersonal = new PersonalInformation();
		tempPersonal.setDateBirth(dto.getBirthDate());
		tempPersonal.setArtist(artist);
		repository.save(tempPersonal);
		return util.sendCreated("success create user data", true, artist);
	}
	
	public ResponseEntity<Response> loginArtist(LoginDto dto){
		
	}
}
