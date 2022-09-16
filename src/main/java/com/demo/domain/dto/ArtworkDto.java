package com.demo.domain.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.demo.domain.ArtWorkWeightDimension;
import com.demo.domain.Artist;
import com.demo.domain.CategoryArtwork;

import lombok.Data;

@Data
public class ArtworkDto {

	@NotNull
	@NotBlank
	private String name;
	
	@NotNull
	@NotBlank
	private String description;
	
	@NotNull
	private Integer stock;
	
	@Lob
	private byte[] photo;
	
	private Boolean status;
	
	@Enumerated(EnumType.STRING)
	private CategoryArtwork category;
	
	@NotNull
	private Artist artist;
	
	@NotNull
	private ArtWorkWeightDimension dimension;
}
