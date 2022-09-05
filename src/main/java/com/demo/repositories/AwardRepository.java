package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.demo.domain.Awards;

public interface AwardRepository extends CrudRepository<Awards, Long>{}
