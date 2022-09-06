package com.demo.util;

@FunctionalInterface
public interface IJwt <T> {
	String generateToken(T obj);
}
