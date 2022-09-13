package com.demo.domain.dto;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.demo.domain.Gender;

import lombok.Data;

@Data
public class PersonalInformationDto {
	
	private String phoneNumber;
	
	private boolean isPhoneNumberPrivate;
	
	private String province;
	
	private boolean isProvincePrivate;
	
	private String city;
	
	private boolean isCityPrivate;
	
	private String address;
	
	private boolean isAddressPrivate;
	
	private Date dateBirth;
	
	private boolean isDateBirthPrivate;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private boolean isGenderPrivate;
}
