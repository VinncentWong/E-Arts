package com.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Artist;
import com.demo.domain.Awards;
import com.demo.domain.Response;
import com.demo.domain.dto.AwardsDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.exception.AwardsNotFoundException;
import com.demo.repositories.ArtistRepository;
import com.demo.repositories.AwardRepository;
import com.demo.util.ResponseUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AwardsService {
	
	private final ArtistRepository artistRepo;
	
	private final AwardRepository awardsRepo;
	
	private final ResponseUtil util;
	
	@Autowired
	public AwardsService(ArtistRepository artistRepo, ResponseUtil util, AwardRepository awardsRepo) {
		this.artistRepo = artistRepo;
		this.util = util;
		this.awardsRepo = awardsRepo;
	}

	public ResponseEntity<Response> createAwards(AwardsDto dto, Long artistId) throws AwardsNotFoundException{
		Artist artist = artistRepo.findById(artistId).orElseThrow(() -> new AwardsNotFoundException());
		Awards awards = new Awards();
		awards.setArtistId(artist);
		return util.sendCreated("success create awards", true, awards);
	}
	
	public ResponseEntity<Response> getAwards(Long artistId)
			throws ArtistNotFoundException, AwardsNotFoundException{
		artistRepo.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		List<Awards> awards = awardsRepo.getAwardsByArtistId(artistId).orElseThrow(() -> new AwardsNotFoundException());
		return util.sendOk("success find awards data", true, awards);
	}
	
	public ResponseEntity<Response> updateAwards(Long artistId, AwardsDto dto)
			throws ArtistNotFoundException, AwardsNotFoundException{
		artistRepo.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		return null;
	}
}
