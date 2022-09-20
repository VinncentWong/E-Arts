package com.demo.domain;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class User {
	
	private Long id;
	
	private String name;
	
	private String email;
	
	private String username;
	
	private String phoneNumber;
	
	private Gender gender;
	
	private Date birthDate;
}
