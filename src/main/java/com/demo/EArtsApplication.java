package com.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EArtsApplication {

	@Value("${JWT_SECRET_KEY}")
	private static String jwtSecret;

	public static void main(String[] args) {
		System.out.println("jwt = " + jwtSecret);
		SpringApplication.run(EArtsApplication.class, args);
	}

}
