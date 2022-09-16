package com.demo.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Dimension {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer length;
	
	private Integer width;
	
	private Integer height;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private ArtWorkWeightDimension artWorkDimension;
	
}
