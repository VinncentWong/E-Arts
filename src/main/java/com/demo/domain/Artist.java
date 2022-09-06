package com.demo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class Artist implements Human{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	
	private String password;
	
	private String name;
	
	private String about;
	
	private String biography;
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date createdAt;
	
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	private Date updatedAt;
	
	@Temporal(TemporalType.DATE)
	private Date deletedAt;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "artist" )
	private List<PersonalInformation> personal;
}
