package com.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Response;
import com.demo.domain.User;
import com.demo.domain.dto.SignUpDto;
import com.demo.repositories.UserRepository;

@Service
@Transactional
public class UserService {

	private final UserRepository userRepo;
	
	@Autowired
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public ResponseEntity<Response> createUser(SignUpDto dto){
		User user = new User();
		user.setEmail(dto.getEmail());
		return null;
	}
}
