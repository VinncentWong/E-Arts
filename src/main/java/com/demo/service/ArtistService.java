package com.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.MapAccessor;
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
import com.demo.exception.ExpertiseNotFoundException;
import com.demo.exception.InternalServerErrorException;
import com.demo.repositories.ArtistRepository;
import com.demo.repositories.ExpertiseRepository;
import com.demo.repositories.PersonalInformationRepository;
import com.demo.util.JwtUtil;
import com.demo.util.ResponseUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class ArtistService {

	private final PersonalInformationRepository repository;
	
	private final BCryptPasswordEncoder bcrypt;
	
	private final ResponseUtil util;
	
	private final ArtistRepository artistRepo;
	
	private final JwtUtil<Artist> jwtUtil;

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
			Map<String, Object> map = new HashMap<>();
			map.put("data", artist);
			map.put("jwt_token", token);
			return util.sendOk("user authenticated", true, map);
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
	
	public ResponseEntity<Response> getArtistExpertise(Long id) throws ArtistNotFoundException, ExpertiseNotFoundException{
		Artist artist = artistRepo.findById(id).orElseThrow(() -> new ArtistNotFoundException());
		List<Expertise> expertise = expertiseRepo.getExpertisesByArtistId(artist.getId()).orElseThrow(() -> new ExpertiseNotFoundException());
		return util.sendOk("data expertise ditemukan", true, expertise);
	}
	
	public ResponseEntity<Response> deleteArtistExpertise(Long idArtist, Long idExpertise) 
			throws NullPointerException, ArtistNotFoundException, ExpertiseNotFoundException{
		artistRepo.findById(idArtist).orElseThrow(() -> new ArtistNotFoundException());
		expertiseRepo.getExpertisesByArtistId(idArtist).orElseThrow(() -> new ExpertiseNotFoundException());
		expertiseRepo.deleteExpertiseDataByExpertiseId(idExpertise);
		return util.sendOk("sukses menghapus data expertise", true, null);
	}
	
	public ResponseEntity<Response> updateArtistExpertise(Long idExpertise, Long idArtist, ExpertiseDto dto) 
			throws ArtistNotFoundException, ExpertiseNotFoundException{
		artistRepo.findById(idArtist).orElseThrow(() -> new ArtistNotFoundException());
		expertiseRepo.getExpertisesByArtistId(idArtist).orElseThrow(() -> new ExpertiseNotFoundException());
		expertiseRepo.updateExpertiseData(dto.getText(), idExpertise);
		return util.sendOk("sukses update data expertise", true, null);
	}
}
