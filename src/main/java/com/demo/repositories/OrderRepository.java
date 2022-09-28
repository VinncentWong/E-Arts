package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.user.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{}
