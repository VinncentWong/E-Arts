package com.demo.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
}
