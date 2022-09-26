package com.demo.domain.dto;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.demo.domain.Role;

import lombok.Data;

@Data
public class SignUpDto {
	
	@NotNull
	@NotBlank
	private String email;
	
	@NotNull
	@NotBlank
	private String username;
	
	@NotNull
	@NotBlank
	private String password;
	
	@NotNull
	private Date birthDate;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Role role;
}
