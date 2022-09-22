package com.demo.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity(name = "users")
@Table(name = "users")
@Data
public class User implements Human{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Transient
	private String name;
	
	private String firstName;

	private String lastName;

	private String userName;

	private String email;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Temporal(TemporalType.DATE)
	private Date birthDate;

	private String password;

	@Temporal(TemporalType.DATE)
	@CreationTimestamp
	private Date createdAt;

	@Temporal(TemporalType.DATE)
	@UpdateTimestamp
	private Date updatedAt;

	@Temporal(TemporalType.DATE)
	private Date deletedAt;

	@Enumerated(EnumType.STRING)
	private Role role;
}
