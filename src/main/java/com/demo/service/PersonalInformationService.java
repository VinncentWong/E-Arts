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
import com.demo.util.ResponseUtil;

@Service
@Transactional
public class PersonalInformationService {

	private final PersonalInformationRepository repository;
	
	private final ArtistRepository artistRepository;
	
	private final ResponseUtil util;
	
	@Autowired
	public PersonalInformationService(PersonalInformationRepository repository, ArtistRepository artistRepository, ResponseUtil util) {
		this.repository = repository;
		this.artistRepository = artistRepository;
		this.util = util;
	}
	
	public ResponseEntity<Response> createPersonalInformation(PersonalInformationDto dto, Long artistId)
		throws ArtistNotFoundException{
		Artist artist = this.artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		PersonalInformation personalInformation = new PersonalInformation();
		personalInformation.setAddress(dto.getAddress());
		personalInformation.setAddressPrivate(dto.isAddressPrivate());
		personalInformation.setArtist(artist);
		personalInformation.setCity(dto.getCity());
		personalInformation.setCityPrivate(dto.isCityPrivate());
		personalInformation.setDateBirth(dto.getDateBirth());
		personalInformation.setDateBirthPrivate(dto.isDateBirthPrivate());
		personalInformation.setGender(dto.getGender());
		personalInformation.setGenderPrivate(dto.isGenderPrivate());
		personalInformation.setPhoneNumber(dto.getPhoneNumber());
		personalInformation.setPhoneNumberPrivate(dto.isPhoneNumberPrivate());
		personalInformation.setProvince(dto.getProvince());
		personalInformation.setProvincePrivate(dto.isProvincePrivate());
		this.repository.save(personalInformation);
		return this.util.sendCreated("sukses membuat personal information", true, artist);
	}

}
