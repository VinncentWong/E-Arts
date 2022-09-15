package com.demo.util;

import java.util.function.Consumer;

@FunctionalInterface
public interface ISetterNullAware{
	public void setIfNotNull(Consumer<? extends Object> consumer);
}
