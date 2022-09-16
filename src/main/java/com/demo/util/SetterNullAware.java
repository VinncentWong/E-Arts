package com.demo.util;

import java.util.Date;
import java.util.function.Consumer;

import com.demo.domain.Gender;

public class SetterNullAware{

	public void setNumber(Consumer<? super Number> consumer, Number data) {
		if(data != null)
			consumer.accept(data);
	}
	
	public void setBoolean(Consumer<? super Boolean> consumer, Boolean data) {
		if(data != null)
			consumer.accept(data);
	}
	
	public void setDate(Consumer<? super Date> consumer, Date data) {
		if(data != null)
			consumer.accept(data);
	}
	
	public void setString(Consumer<? super String> consumer, String data) {
		if(data != null)
			consumer.accept(data);
	}
	
	public void setGender(Consumer<? super Gender> consumer, Gender data) {
		if(data != null)
			consumer.accept(data);
	}
}