package com.demo.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity(name = "users")
@Table(name = "users")
@Data
public class User implements Human{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String email;
	
	private String username;

	private String password;
	
	private String phoneNumber;
	
	private Gender gender;
	
	private Date birthDate;

	private Role role;

	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date createdAt;

	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date updatedAt;

	@Temporal(TemporalType.DATE)
	private Date deletedAt;
}
