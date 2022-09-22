package com.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.user.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM user WHERE email = ?1 LIMIT 1")
	Optional<User> getUserByEmail(String email);

}
