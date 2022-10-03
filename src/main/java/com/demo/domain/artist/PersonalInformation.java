package com.demo.domain.artist;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.demo.domain.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "artist")
public class PersonalInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String phoneNumber;
	
	private Boolean isPhoneNumberPrivate;
	
	private String province;
	
	private Boolean isProvincePrivate;
	
	private String city;
	
	private Boolean isCityPrivate;
	
	private String address;
	
	private Boolean isAddressPrivate;
	
	@Temporal(TemporalType.DATE)
	private Date dateBirth;
	
	private Boolean isDateBirthPrivate;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private Boolean isGenderPrivate;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore // ignore this property to serialized into JSON because it will caused circular depedency
	private Artist artist;
}
