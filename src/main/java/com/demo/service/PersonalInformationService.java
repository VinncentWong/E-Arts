package com.demo.service;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import javax.transaction.Transactional;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Artist;
import com.demo.domain.PersonalInformation;
import com.demo.domain.Response;
import com.demo.domain.dto.PersonalInformationDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.exception.PersonalInformationNotFoundException;
import com.demo.mapper.PersonalInformationMapper;
import com.demo.repositories.ArtistRepository;
import com.demo.repositories.PersonalInformationRepository;
import com.demo.util.ResponseUtil;

@Service
@Transactional
public class PersonalInformationService {

	private final PersonalInformationRepository repository;
	
	private final ArtistRepository artistRepository;
	
	private final ResponseUtil util;
	
	private final SqlSessionFactory session;
	
	@Autowired
	public PersonalInformationService(PersonalInformationRepository repository, ArtistRepository artistRepository, ResponseUtil util, SqlSessionFactory session) {
		this.repository = repository;
		this.artistRepository = artistRepository;
		this.util = util;
		this.session = session;
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
		try(SqlSession session = this.session.openSession()){
			PersonalInformationMapper mapper = session.getMapper(PersonalInformationMapper.class);
			return null;
		}
		catch(Exception ex) {
			
		}
		return this.util.sendOk("sukses mengupdate data personal information", true, null);
	}
}