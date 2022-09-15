package com.demo.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "artist")
@DynamicUpdate(value = true)
public class PersonalInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String phoneNumber;
	
	private boolean isPhoneNumberPrivate;
	
	private String province;
	
	private boolean isProvincePrivate;
	
	private String city;
	
	private boolean isCityPrivate;
	
	private String address;
	
	private boolean isAddressPrivate;
	
	@Temporal(TemporalType.DATE)
	private Date dateBirth;
	
	private boolean isDateBirthPrivate;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private boolean isGenderPrivate;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore // ignore this property to serialized into JSON because it will caused circular depedency
	private Artist artist;
}
