package com.demo.domain.mybatis;

import java.sql.JDBCType;
import java.util.Date;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import com.demo.domain.Gender;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonalInformation extends SqlTable{

	private SqlColumn<String> phoneNumber = column("phone_number", JDBCType.VARCHAR);
	
	private SqlColumn<Boolean> isPhoneNumberPrivate = column("is_phone_number_private", JDBCType.BOOLEAN);
	
	private SqlColumn<String> province = column("province", JDBCType.VARCHAR);
	
	private SqlColumn<Boolean> isProvincePrivate = column("is_province_private", JDBCType.BOOLEAN);
	
	private SqlColumn<String> city = column("city", JDBCType.VARCHAR);
	
	private SqlColumn<Boolean> isCityPrivate = column("is_city_private", JDBCType.BOOLEAN);
	
	private SqlColumn<String> address = column("address", JDBCType.VARCHAR);
	
	private SqlColumn<Boolean> isAddressPrivate = column("is_address_private", JDBCType.BOOLEAN);
	
	private SqlColumn<Date> dateBirth = column("date_birth", JDBCType.DATE);
	
	private SqlColumn<Boolean> isDateBirthPrivate = column("is_date_birth_private", JDBCType.BOOLEAN);
	
	private SqlColumn<Gender> gender = column("gender", JDBCType.VARCHAR);
	
	private SqlColumn<Boolean> isGenderPrivate = column("is_date_birth_private", JDBCType.BOOLEAN);
	
	protected PersonalInformation() {
		super("personal_information");
	}

}
