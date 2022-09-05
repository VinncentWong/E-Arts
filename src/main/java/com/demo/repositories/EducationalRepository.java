package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.Educational;

@Repository
public interface EducationalRepository extends CrudRepository<Educational, Long>{}
