package com.demo.domain.dto;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.demo.domain.Gender;

import lombok.Data;

@Data
public class PersonalInformationDto {
	
	private String phoneNumber;
	
	private Boolean isPhoneNumberPrivate;
	
	private String province;
	
	private Boolean isProvincePrivate;
	
	private String city;
	
	private Boolean isCityPrivate;
	
	private String address;
	
	private Boolean isAddressPrivate;
	
	private Date dateBirth;
	
	private Boolean isDateBirthPrivate;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private Boolean isGenderPrivate;
}
