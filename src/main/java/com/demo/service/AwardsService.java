package com.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Response;
import com.demo.domain.artist.Artist;
import com.demo.domain.artist.Awards;
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

	public ResponseEntity<Response> createAwards(AwardsDto dto, Long artistId) throws ArtistNotFoundException{
		log.info("awards dto = " + dto.getAwards());
		Artist artist = artistRepo.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		Awards awards = new Awards();
		awards.setArtistId(artist);
		awards.setAwards(dto.getAwards());
		awards = awardsRepo.save(awards);
		return util.sendCreated("success create awards", true, awards);
	}
	
	public ResponseEntity<Response> getAwards(Long artistId)
			throws ArtistNotFoundException, AwardsNotFoundException{
		artistRepo.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		List<Awards> awards = awardsRepo.getAwardsByArtistId(artistId).orElseThrow(() -> new AwardsNotFoundException());
		return util.sendOk("success find awards data", true, awards);
	}
	
	public ResponseEntity<Response> updateAwards(Long awardsId, Long artistId, AwardsDto dto)
			throws ArtistNotFoundException, AwardsNotFoundException{
		artistRepo.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		awardsRepo.findById(awardsId).orElseThrow(() -> new AwardsNotFoundException());
		awardsRepo.updateAwards(artistId, awardsId, dto.getAwards());
		return util.sendOk("sukses update awards data", true, null);
	}
	
	public ResponseEntity<Response> deleteAwards(Long awardsId, Long artistId)
			throws ArtistNotFoundException, AwardsNotFoundException{
		artistRepo.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		awardsRepo.findById(awardsId).orElseThrow(() -> new AwardsNotFoundException());
		awardsRepo.deleteAwards(artistId, awardsId);
		return util.sendOk("success delete awards data", true, null);
	}
}
