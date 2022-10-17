package com.demo.domain.artist;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.demo.domain.Human;
import com.demo.domain.Role;

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
	
	private String username;
	
	private String about;
	
	private String biography;

	private String firstName;

	private String lastName;

	private String province;

	private String city;
	
	@Enumerated(EnumType.STRING)
	private Role role;

	@ColumnDefault("0")
	private Integer accumulativeLike;

	@ColumnDefault("0")
	private Integer accumulativeArtwork;

	@ColumnDefault("0")
	private Integer accumulativePublication;

	@ColumnDefault("0")
	private Integer accumulativeSells;
	
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
	
	@OneToOne(mappedBy = "artist", cascade = CascadeType.ALL)
	private PersonalInformation personalInformation;
	
	@OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
	private List<ArtWork> artwork;
}
