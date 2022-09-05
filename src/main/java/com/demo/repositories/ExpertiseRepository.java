package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.Expertise;

@Repository
public interface ExpertiseRepository extends CrudRepository<Expertise, Long> {}
