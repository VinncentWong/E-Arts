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
import com.demo.exception.ArtistNotFoundException;
import com.demo.exception.InternalServerErrorException;
import com.demo.repositories.ArtistRepository;
import com.demo.repositories.PersonalInformationRepository;
import com.demo.util.JwtUtil;
import com.demo.util.ResponseUtil;

@Service
public class ArtistService {

	private final PersonalInformationRepository repository;
	
	private final BCryptPasswordEncoder bcrypt;
	
	private final ResponseUtil util;
	
	private final ArtistRepository artistRepo;
	
	private final JwtUtil<Artist> jwtUtil;
	@Autowired
	public ArtistService(PersonalInformationRepository repository, BCryptPasswordEncoder bcrypt, ResponseUtil util, ArtistRepository artistRepo, JwtUtil<Artist> jwtUtil) {
		this.repository = repository;
		this.bcrypt = bcrypt;
		this.util = util;
		this.artistRepo = artistRepo;
		this.jwtUtil = jwtUtil;
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
	
	public ResponseEntity<Response> loginArtist(LoginDto dto) throws ArtistNotFoundException, InternalServerErrorException{
		Artist artist = artistRepo.getArtistByEmail(dto.getEmail()).orElseThrow(() -> new ArtistNotFoundException());
		if(bcrypt.matches(dto.getPassword(), artist.getPassword())) {
			String token = jwtUtil.generateToken(jwtUtil::implementationGenerateToken, artist);
			return util.sendOk("user authenticated", true, token);
		} else {
			throw new InternalServerErrorException();
		}
	}
}
