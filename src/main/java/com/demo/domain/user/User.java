package com.demo.domain.user;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.demo.domain.Gender;
import com.demo.domain.Human;
import com.demo.domain.Role;

import lombok.Data;

@Entity(name = "users")
@Table(name = "users")
@Data
public class User implements Human{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;

	private String lastName;

	private String username;

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

	private String phoneNumber;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private List<Address> address;
}
