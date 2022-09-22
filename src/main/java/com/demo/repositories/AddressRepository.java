package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.demo.domain.user.Address;

public interface AddressRepository extends CrudRepository<Address, Long>{}
