package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Artist;
import com.demo.domain.Educational;
import com.demo.domain.Response;
import com.demo.domain.dto.EducationalDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.repositories.ArtistRepository;
import com.demo.repositories.EducationalRepository;
import com.demo.util.ResponseUtil;

@Service
public class EducationalService {

	private final EducationalRepository educationalRepository;
	
	private final ArtistRepository artistRepository;
	
	private final ResponseUtil util;
	
	@Autowired
	public EducationalService(EducationalRepository repository, ResponseUtil util, ArtistRepository artistRepository) {
		this.educationalRepository = repository;
		this.util = util;
		this.artistRepository = artistRepository;
	}
	
	public ResponseEntity<Response> createEducational(EducationalDto dto, Long artistId) throws ArtistNotFoundException{
		Artist artist = this.artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		Educational educational = new Educational();
		educational.setArtistId(artist);
		this.educationalRepository.save(educational);
		return this.util.sendCreated("sukses membuat educational", true, educational);
	}
	
	public ResponseEntity<Response> getEducationals(Long artistId) throws ArtistNotFoundException{
		this.artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		Optional<List<Educational>> list = this.educationalRepository.getEducationalsByArtistId(artistId);
		if(list.isEmpty()) {
			return this.util.sendOk("data educational tidak ditemukan", true, null);
		}
		return this.util.sendOk("data educational ditemukan", true, list.get());
	}
	
	public ResponseEntity<Response> getEducational(Long artistId, Long educationalId) throws ArtistNotFoundException{
		this.artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		Optional<Educational> education = this.educationalRepository.getEducationalByArtistId(artistId, educationalId);
		if(education.isEmpty()) {
			return this.util.sendOk("data educational tidak ditemukan", true, null);
		} 
		return this.util.sendOk("data educational ditemukan", true, education.get());
	}
	
	public ResponseEntity<Response> updateEducational(Long artistId, Long educationalId, EducationalDto dto) throws ArtistNotFoundException{
		this.artistRepository.findById(educationalId).orElseThrow(() -> new ArtistNotFoundException());
	}
}
