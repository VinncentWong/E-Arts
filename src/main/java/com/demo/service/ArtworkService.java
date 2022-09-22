package com.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.domain.ArtWork;
import com.demo.domain.ArtWorkWeightDimension;
import com.demo.domain.Artist;
import com.demo.domain.Dimension;
import com.demo.domain.Photo;
import com.demo.domain.Response;
import com.demo.domain.dto.ArtworkDto;
import com.demo.exception.ArtistNotFoundException;
import com.demo.exception.ArtworkNotFoundException;
import com.demo.exception.PhotoNotFoundException;
import com.demo.repositories.ArtistRepository;
import com.demo.repositories.ArtworkRepository;
import com.demo.util.ResponseUtil;
import com.demo.util.SetterNullAware;


@Service
@Transactional
public class ArtworkService {

	private final ArtworkRepository repository;
	
	private final ArtistRepository artistRepository;
	
	private final ResponseUtil util;
	
	private boolean processValid;
	
	@Autowired
	public ArtworkService(ArtworkRepository repository, ArtistRepository artistRepository, ResponseUtil util) {
		this.repository = repository;
		this.artistRepository = artistRepository;
		this.util = util;
	}
	
	public ResponseEntity<Response> createArtwork(ArtworkDto dto, Long artistId, List<MultipartFile> files) throws ArtistNotFoundException{
		Artist artist = this.artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		ArtWork artwork = new ArtWork();
		ArtWorkWeightDimension artworkDimension = new ArtWorkWeightDimension();
		Dimension dimension = new Dimension();
		Dimension temp = dto.getDimension().getDimension();
		List<Photo> listPhoto = new ArrayList<>();
		this.processValid = true;
		files.forEach((b) -> {
			try {
				Photo photo = new Photo();
				photo.setPhoto(b.getBytes());
				listPhoto.add(photo);
			}
			catch(IOException ex) {
				this.processValid = false;
				return;
			}
		});
		if(!this.processValid)
			return this.util.sendInternalServerError("terjadi kesalahan pada saat melakukan migrasi data", false);
		listPhoto.forEach((p) -> {
			p.setArtwork(artwork);
		});
		artwork.setArtist(artist);
		artwork.setCategory(dto.getCategory());
		artwork.setDescription(dto.getDescription());
		artwork.setName(dto.getName());
		artwork.setPhoto(listPhoto);
		artwork.setStatus(dto.getStatus());
		artwork.setStock(dto.getStock());
		artworkDimension.setWeight(dto.getDimension().getWeight());
		artwork.setDimension(artworkDimension);
		dimension.setHeight(temp.getHeight());
		dimension.setLength(temp.getLength());
		dimension.setWidth(temp.getWidth());
		artwork.getDimension().setDimension(dimension);
		this.repository.save(artwork);
		return this.util.sendCreated("sukses membuat artwork!", true, artwork);
	}
	
	public ResponseEntity<Response> getArtwork(Long artistId) throws ArtworkNotFoundException{
		List<ArtWork> listArtwork = this.repository.getArtworks(artistId).orElseThrow(() -> new ArtworkNotFoundException());
		return this.util.sendOk("sukses mendapatkan artwork", true, listArtwork);
	}
	
	public ResponseEntity<Response> updateArtwork(Long artworkId, Long artistId, ArtworkDto dto) throws ArtworkNotFoundException{
		ArtWork artwork = this.repository.getArtwork(artistId, artworkId).orElseThrow(() -> new ArtworkNotFoundException());
		ArtWorkWeightDimension artworkWeight = artwork.getDimension();
		Dimension dimension = artwork.getDimension().getDimension();
		SetterNullAware setter = new SetterNullAware();
		setter.setString(artwork::setName, dto.getName());
		setter.setString(artwork::setDescription, dto.getDescription());
		setter.setNumber(artwork::setStock, dto.getStock());
		setter.setBoolean(artwork::setStatus, dto.getStatus());
		setter.setCategory(artwork::setCategory, dto.getCategory());
		setter.setDimension(artwork::setDimension, dto.getDimension());
		setter.setNumber(artworkWeight::setWeight, dto.getDimension().getWeight());
		setter.setNumber(dimension::setLength, dto.getDimension().getDimension().getLength());
		setter.setNumber(dimension::setHeight, dto.getDimension().getDimension().getHeight());
		setter.setNumber(dimension::setWidth, dto.getDimension().getDimension().getWidth());
		this.repository.save(artwork);
		return this.util.sendOk("sukses mengupdate data artwork", true, null);
	}
	
	public ResponseEntity<Response> deleteArtwork(Long artworkId, Long artistId) throws ArtworkNotFoundException, ArtistNotFoundException{
		ArtWork artwork = this.repository.findById(artworkId).orElseThrow(() -> new ArtworkNotFoundException());
		this.artistRepository.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		ArtWorkWeightDimension artworkWeight = artwork.getDimension();
		Dimension dimension = artwork.getDimension().getDimension();
		this.repository.deleteDimension(dimension.getId());
		this.repository.deleteArtworkWeight(artworkWeight.getId());
		this.repository.deleteArtwork(artistId, artworkId);
		return this.util.sendOk("sukses menghapus data artwork", true, null);
	}
	
	public ResponseEntity<Response> updatePhoto(Long artworkId, Long photoId, MultipartFile file)
			throws IOException, PhotoNotFoundException, ArtworkNotFoundException{
		this.repository.findById(artworkId).orElseThrow(() -> new ArtworkNotFoundException());
		this.repository.getPhoto(photoId).orElseThrow(() -> new PhotoNotFoundException());
		this.repository.updatePhoto(artworkId, photoId, file.getBytes());
		return this.util.sendOk("sukses mengupdate data photo", true, null);
	}
}
