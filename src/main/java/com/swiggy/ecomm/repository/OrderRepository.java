package com.swiggy.ecomm.repository;

import com.swiggy.ecomm.model.Order;
import com.swiggy.ecomm.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    boolean existsByCustomerId(Long customerId);

    List<Order> findByStatus(OrderStatus status);
}
