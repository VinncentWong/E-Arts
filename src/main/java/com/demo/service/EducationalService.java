package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.repositories.EducationalRepository;

@Service
public class EducationalService {

	private final EducationalRepository repository;
	
	@Autowired
	public EducationalService(EducationalRepository repository) {
		this.repository = repository;
	}
	
	
}
