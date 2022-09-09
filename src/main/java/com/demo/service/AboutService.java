package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Artist;
import com.demo.domain.Response;
import com.demo.domain.dto.AboutDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.repositories.ArtistRepository;
import com.demo.util.ResponseUtil;

@Service
public class AboutService {
	
	private final ArtistRepository repository;
	
	private final ResponseUtil util;
	
	@Autowired
	public AboutService(ArtistRepository repository, ResponseUtil util) {
		this.repository = repository;
		this.util = util;
	}
	
	public ResponseEntity<Response> createAbout(AboutDto dto, Long artistId) throws ArtistNotFoundException{
		Artist artist = repository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		artist.setAbout(dto.getAbout());
		this.repository.save(artist);
		return util.sendCreated("sukses menyimpan awards data", true, artist);
	}
}
