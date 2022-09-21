package com.demo.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Response;
import com.demo.domain.User;
import com.demo.domain.dto.SignUpDto;
import com.demo.repositories.UserRepository;
import com.demo.util.ResponseUtil;

@Service
@Transactional
public class UserService {

	private final UserRepository userRepo;
	
	private final ResponseUtil util;
	
	@Autowired
	public UserService(UserRepository userRepo, ResponseUtil util) {
		this.userRepo = userRepo;
		this.util = util;
	}
	
	public ResponseEntity<Response> createUser(SignUpDto dto){
		Optional<User> tempUser = this.userRepo.getUserByEmail(dto.getEmail());
		if(tempUser.isPresent()){
			
		}
		User user = new User();
		user.setEmail(dto.getEmail());
		return null;
	}
}
