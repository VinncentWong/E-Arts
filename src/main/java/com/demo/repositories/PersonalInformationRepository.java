package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.PersonalInformation;

@Repository
public interface PersonalInformationRepository extends CrudRepository<PersonalInformation, Long>{}
