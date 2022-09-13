package com.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Artist;
import com.demo.domain.PersonalInformation;
import com.demo.domain.Response;
import com.demo.domain.dto.PersonalInformationDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.repositories.ArtistRepository;
import com.demo.repositories.PersonalInformationRepository;

@Service
@Transactional
public class PersonalInformationService {

	private final PersonalInformationRepository repository;
	
	private final ArtistRepository artistRepository;
	
	@Autowired
	public PersonalInformationService(PersonalInformationRepository repository, ArtistRepository artistRepository) {
		this.repository = repository;
		this.artistRepository = artistRepository;
	}

}
