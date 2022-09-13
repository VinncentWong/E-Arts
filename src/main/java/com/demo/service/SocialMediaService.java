package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Artist;
import com.demo.domain.Response;
import com.demo.domain.SocialMedia;
import com.demo.domain.dto.SocialMediaDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.repositories.ArtistRepository;
import com.demo.repositories.SocialMediaRepository;
import com.demo.util.ResponseUtil;

@Service
public class SocialMediaService {

	private final SocialMediaRepository repository;
	
	private final ArtistRepository artistRepository;
	
	private final ResponseUtil util;
	
	@Autowired
	public SocialMediaService(SocialMediaRepository repository, ArtistRepository artistRepository, ResponseUtil util) {
		this.repository = repository;
		this.artistRepository = artistRepository;
		this.util = util;
	}
	
	public ResponseEntity<Response> createSocialMedia(SocialMediaDto dto, Long artistId)
		throws ArtistNotFoundException{
		SocialMedia socialMedia = new SocialMedia();
		Artist artist = this.artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		socialMedia.setArtist(artist);
		this.repository.save(socialMedia);
		return util.sendOk("sukses membuat social media", true, artist);
	}
	
	
}
