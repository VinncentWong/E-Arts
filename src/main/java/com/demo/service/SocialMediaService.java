package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Artist;
import com.demo.domain.Response;
import com.demo.domain.SocialMedia;
import com.demo.domain.dto.SocialMediaDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.exception.SocialMediaNotFoundException;
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
		socialMedia.setSocialMedia(dto.getSocialMedia());
		this.repository.save(socialMedia);
		return util.sendCreated("sukses membuat social media", true, artist);
	}
	
	public ResponseEntity<Response> getSocialMedias(Long artistId) throws SocialMediaNotFoundException{
		List<SocialMedia> list = this.repository.getSocialMedias(artistId).orElseThrow(() -> new SocialMediaNotFoundException());
		return this.util.sendOk("sukses mendapatkan data social medias", true, list);
	}
	
	public ResponseEntity<Response> getSocialMedia(Long artistId, Long socialMediaId) throws SocialMediaNotFoundException{
		SocialMedia sosmed = this.repository.getSocialMedia(artistId, socialMediaId).orElseThrow(() -> new SocialMediaNotFoundException());
		return this.util.sendOk("sukses mendapatkan data social media", true, sosmed);
	}
	
	public ResponseEntity<Response> updateSocialMedia(Long artistId, Long socialMediaId, SocialMediaDto dto)
			throws SocialMediaNotFoundException, ArtistNotFoundException{
		this.artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		this.repository.findById(socialMediaId).orElseThrow(() -> new SocialMediaNotFoundException());
		this.repository.updateSocialMedia(artistId, socialMediaId, dto.getSocialMedia());
		return this.util.sendOk("sukses mengupdate data social media", true, null);
	}
	
	public ResponseEntity<Response> deleteSocialMedia(Long artistId, Long socialMediaId)
			throws SocialMediaNotFoundException, ArtistNotFoundException{
		this.artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		this.repository.findById(socialMediaId).orElseThrow(() -> new SocialMediaNotFoundException());
		this.repository.deleteSocialMedia(artistId, socialMediaId);
		return this.util.sendOk("sukses menghapus data social media", true, null);
	}
}
