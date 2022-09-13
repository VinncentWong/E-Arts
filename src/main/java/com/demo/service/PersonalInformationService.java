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
import com.demo.exception.PersonalInformationNotFoundException;
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
		personalInformation.setAddressPrivate(dto.getIsAddressPrivate());
		personalInformation.setArtist(artist);
		personalInformation.setCity(dto.getCity());
		personalInformation.setCityPrivate(dto.getIsCityPrivate());
		personalInformation.setDateBirth(dto.getDateBirth());
		personalInformation.setDateBirthPrivate(dto.getIsDateBirthPrivate());
		personalInformation.setGender(dto.getGender());
		personalInformation.setGenderPrivate(dto.getIsGenderPrivate());
		personalInformation.setPhoneNumber(dto.getPhoneNumber());
		personalInformation.setPhoneNumberPrivate(dto.getIsPhoneNumberPrivate());
		personalInformation.setProvince(dto.getProvince());
		personalInformation.setProvincePrivate(dto.getIsProvincePrivate());
		this.repository.save(personalInformation);
		return this.util.sendCreated("sukses membuat personal information", true, artist);
	}

	public ResponseEntity<Response> getPersonalInformation(Long artistId) throws PersonalInformationNotFoundException{
		PersonalInformation personalInformation = this.repository.getPersonalInformationByArtistId(artistId).orElseThrow(() -> new PersonalInformationNotFoundException());
		return this.util.sendOk("sukses menemukan data personal information", true, personalInformation);
	}
	
	public ResponseEntity<Response> updatePersonalInformation(Long artistId, PersonalInformationDto dto, Long personalInformationId) throws PersonalInformationNotFoundException{
		PersonalInformation personalInformation = this.repository.findById(personalInformationId).orElseThrow(() -> new PersonalInformationNotFoundException());
		if(dto.getAddress() != null) {
			personalInformation.setAddress(dto.getAddress());
		}
		if(dto.getCity() != null) {
			personalInformation.setCity(dto.getCity());
		}
		if(dto.getDateBirth() != null) {
			personalInformation.setDateBirth(dto.getDateBirth());
		}
		if(dto.getGender() != null) {
			personalInformation.setGender(dto.getGender());
		}
		if(dto.getPhoneNumber() != null) {
			personalInformation.setPhoneNumber(dto.getPhoneNumber());
		}
		if(dto.getProvince() != null) {
			personalInformation.setProvince(dto.getProvince());
		}
		if(dto.getIsAddressPrivate() != null) {
			personalInformation.setAddressPrivate(dto.getIsAddressPrivate());
		}
		if(dto.getIsCityPrivate() != null) {
			personalInformation.setCityPrivate(dto.getIsCityPrivate());
		}
		if(dto.getIsDateBirthPrivate() != null) {
			personalInformation.setDateBirthPrivate(dto.getIsDateBirthPrivate());
		}
		if(dto.getIsGenderPrivate() != null) {
			personalInformation.setGenderPrivate(dto.getIsGenderPrivate());
		}
		if(dto.getIsPhoneNumberPrivate() != null) {
			personalInformation.setPhoneNumberPrivate(dto.getIsPhoneNumberPrivate());
		}
		if(dto.getIsProvincePrivate() != null) {
			personalInformation.setProvincePrivate(dto.getIsProvincePrivate());
		}
		return this.util.sendOk("sukses mengupdate data personal information", true, null);
	}
}
