package com.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.user.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{

    @Query(nativeQuery = true, value = "SELECT * FROM order WHERE order = ?1")
    Optional<List<Order>> getOrders(Long userId);

    @Query(nativeQuery = true, value = "SELECT * FROM order WHERE user_id = ?1 AND id = ?2")
    Optional<Order> getOrder(Long userId, Long orderId);
}
