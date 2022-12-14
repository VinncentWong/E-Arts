package com.demo.domain.dto;

import java.util.List;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.demo.domain.artist.ArtWorkWeightDimension;
import com.demo.domain.artist.CategoryArtwork;

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
	private List<byte[]> photo;
	
	@NotNull
	private Boolean status;
	
	@NotNull
	private CategoryArtwork category;
	
	@NotNull
	private ArtWorkWeightDimension dimension;

}
