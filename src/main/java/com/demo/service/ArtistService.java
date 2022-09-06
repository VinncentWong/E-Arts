package com.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.domain.Artist;
import com.demo.domain.Expertise;
import com.demo.domain.PersonalInformation;
import com.demo.domain.Response;
import com.demo.domain.dto.ExpertiseDto;
import com.demo.domain.dto.LoginDto;
import com.demo.domain.dto.SignUpDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.exception.InternalServerErrorException;
import com.demo.repositories.ArtistRepository;
import com.demo.repositories.ExpertiseRepository;
import com.demo.repositories.PersonalInformationRepository;
import com.demo.util.JwtUtil;
import com.demo.util.ResponseUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArtistService {

	private final PersonalInformationRepository repository;
	
	private final BCryptPasswordEncoder bcrypt;
	
	private final ResponseUtil util;
	
	private final ArtistRepository artistRepo;
	
	private final JwtUtil<Artist> jwtUtil;
	
	private final PersonalInformationRepository personalRepo;

	private final ExpertiseRepository expertiseRepo;
	@Autowired
	public ArtistService(PersonalInformationRepository repository, BCryptPasswordEncoder bcrypt, 
			ResponseUtil util, ArtistRepository artistRepo, JwtUtil<Artist> jwtUtil, 
			PersonalInformationRepository personalRepo,
			ExpertiseRepository expertiseRepo) {
		this.repository = repository;
		this.bcrypt = bcrypt;
		this.util = util;
		this.artistRepo = artistRepo;
		this.jwtUtil = jwtUtil;
		this.personalRepo = personalRepo;
		this.expertiseRepo = expertiseRepo;
	}
	
	public ResponseEntity<Response> createArtist(SignUpDto dto){
		Artist artist = new Artist();
		artist.setEmail(dto.getEmail());
		artist.setName(dto.getName());
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
	
	public ResponseEntity<Response> getArtistById(Long id) throws ArtistNotFoundException{
		log.info("masuk ke get artist by id");
		Artist artist = artistRepo.findById(id).orElseThrow(() -> new ArtistNotFoundException());
		log.info("artist = " + artist);
		return util.sendOk("data artis ditemukan", true, artist);
	}
	
	public ResponseEntity<Response> addArtistExpertise(Long id, ExpertiseDto dto) throws ArtistNotFoundException{
		Artist artist = artistRepo.findById(id).orElseThrow(() -> new ArtistNotFoundException());
		Expertise expertise = new Expertise();
		expertise.setExpertise(dto.getText());
		expertise.setArtist(artist);
		expertiseRepo.save(expertise);
		return util.sendCreated("sukses menambahkan expertise", true, expertise);
	}
	
	public ResponseEntity<Response> getArtistExpertise(Long id) throws ArtistNotFoundException{
		Artist artist = artistRepo.findById(id).orElseThrow(() -> new ArtistNotFoundException());
		Optional<List<Expertise>> expertise = expertiseRepo.getExpertisesByArtistId(artist.getId());
		if(expertise.isEmpty()) {
			return util.sendOk("data expertise tidak ditemukan", true, expertise);
		}
		return util.sendOk("data expertise ditemukan", true, expertise);
	}
	
	public ResponseEntity<Response> deleteArtistExpertise(Long idArtist, Long idExpertise) throws NullPointerException{
		Optional<List<Expertise>> expertise = expertiseRepo.getExpertisesByArtistId(idArtist);
		if(expertise.isEmpty()) {
			return util.sendOk("data expertise tidak ditemukan", true, expertise);
		}
		List<Expertise> list = expertise.get();
		list.removeIf((ex) -> ex.getId() == idExpertise);
		return util.sendOk("sukses menghapus data expertise", true, null);
	}
}
