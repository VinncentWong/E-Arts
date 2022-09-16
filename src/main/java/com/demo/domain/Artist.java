package com.demo.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "personalInformation")
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "artistId")
	private List<Awards> award;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "artistId")
	private List<Educational> educational;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "artist")
	private List<Expertise> expertise;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "artist")
	private List<Publications> publication;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "artist")
	private List<SocialMedia> socialMedia;
	
	@OneToOne(mappedBy = "artist")
	private PersonalInformation personalInformation;
	
	@OneToMany(mappedBy = "artist")
	private List<ArtWork> artwork;
}
