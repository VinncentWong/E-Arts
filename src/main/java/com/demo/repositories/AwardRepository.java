package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.Awards;

@Repository
public interface AwardRepository extends CrudRepository<Awards, Long>{}
