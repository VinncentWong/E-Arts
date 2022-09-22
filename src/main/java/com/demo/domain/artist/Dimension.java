package com.demo.domain.artist;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "artWorkDimension")
public class Dimension {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer length;
	
	private Integer width;
	
	private Integer height;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private ArtWorkWeightDimension artWorkDimension;
	
}
