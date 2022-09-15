package com.demo.util;

import java.util.Date;
import java.util.function.Consumer;

public class SetterNullAware<T>{

	public void setNumber(Consumer<? super Number> consumer, T object, Number data) {
		if(data != null)
			consumer.accept(data);
	}
	
	public void setBoolean(Consumer<? super Boolean> consumer, T object, Boolean data) {
		if(data != null)
			consumer.accept(data);
	}
	
	public void setDate(Consumer<? super Date> consumer, T object,  Date data) {
		if(data != null)
			consumer.accept(data);
	}
	
	public void setString(Consumer<? super String> consumer, T object, String data) {
		if(data != null)
			consumer.accept(data);
	}
}
