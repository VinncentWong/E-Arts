package com.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Response;
import com.demo.domain.artist.Artist;
import com.demo.domain.artist.PersonalInformation;
import com.demo.domain.dto.PersonalInformationDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.exception.PersonalInformationNotFoundException;
import com.demo.repositories.ArtistRepository;
import com.demo.repositories.PersonalInformationRepository;
import com.demo.util.ResponseUtil;
import com.demo.util.SetterNullAware;

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
		personalInformation.setIsAddressPrivate(dto.getIsAddressPrivate());
		personalInformation.setArtist(artist);
		personalInformation.setCity(dto.getCity());
		personalInformation.setIsCityPrivate(dto.getIsCityPrivate());
		personalInformation.setDateBirth(dto.getDateBirth());
		personalInformation.setIsDateBirthPrivate(dto.getIsDateBirthPrivate());
		personalInformation.setGender(dto.getGender());
		personalInformation.setIsGenderPrivate(dto.getIsGenderPrivate());
		personalInformation.setPhoneNumber(dto.getPhoneNumber());
		personalInformation.setIsPhoneNumberPrivate(dto.getIsPhoneNumberPrivate());
		personalInformation.setProvince(dto.getProvince());
		personalInformation.setIsProvincePrivate(dto.getIsProvincePrivate());
		this.repository.save(personalInformation);
		return this.util.sendCreated("sukses membuat personal information", true, artist);
	}

	public ResponseEntity<Response> getPersonalInformation(Long artistId) throws PersonalInformationNotFoundException{
		PersonalInformation personalInformation = this.repository.getPersonalInformationByArtistId(artistId).orElseThrow(() -> new PersonalInformationNotFoundException());
		return this.util.sendOk("sukses menemukan data personal information", true, personalInformation);
	}
	
	public ResponseEntity<Response> updatePersonalInformation(PersonalInformationDto dto, Long personalInformationId) 
			throws PersonalInformationNotFoundException, ArtistNotFoundException{
		SetterNullAware setter = new SetterNullAware();
		PersonalInformation personalInformation = this.repository.findById(personalInformationId).orElseThrow(() -> new PersonalInformationNotFoundException());
		setter.setString(personalInformation::setAddress, dto.getAddress());
		setter.setBoolean(personalInformation::setIsAddressPrivate,  dto.getIsAddressPrivate());
		setter.setString(personalInformation::setCity, dto.getCity());
		setter.setBoolean(personalInformation::setIsCityPrivate, dto.getIsCityPrivate());
		setter.setDate(personalInformation::setDateBirth, dto.getDateBirth());
		setter.setBoolean(personalInformation::setIsDateBirthPrivate, dto.getIsDateBirthPrivate());
		setter.setGender(personalInformation::setGender, dto.getGender());
		setter.setBoolean(personalInformation::setIsGenderPrivate, dto.getIsGenderPrivate());
		setter.setString(personalInformation::setPhoneNumber, dto.getPhoneNumber());
		setter.setBoolean(personalInformation::setIsPhoneNumberPrivate, dto.getIsPhoneNumberPrivate());
		setter.setString(personalInformation::setProvince, dto.getProvince());
		setter.setBoolean(personalInformation::setIsProvincePrivate, dto.getIsProvincePrivate());
		this.repository.save(personalInformation);
		return this.util.sendOk("sukses mengupdate data personal information", true, null);
	}
	
	public ResponseEntity<Response> removePersonalInformation(Long personalInformationId) 
			throws ArtistNotFoundException, PersonalInformationNotFoundException{
		this.repository.findById(personalInformationId).orElseThrow(() -> new PersonalInformationNotFoundException());
		this.repository.deletePersonalInformation(personalInformationId);
		return util.sendOk("sukses menghapus data personal information", true, null);
	}
}
