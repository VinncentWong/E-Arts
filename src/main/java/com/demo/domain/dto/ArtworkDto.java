package com.demo.domain.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

import com.demo.domain.ArtWorkWeightDimension;
import com.demo.domain.Artist;
import com.demo.domain.CategoryArtwork;

import lombok.Data;

@Data
public class ArtworkDto {

	private String name;
	
	private String description;
	
	private Integer stock;
	
	@Lob
	private byte[] photo;
	
	private boolean status;
	
	@Enumerated(EnumType.STRING)
	private CategoryArtwork category;
	
	private Artist artist;
	
	private ArtWorkWeightDimension dimension;
}
