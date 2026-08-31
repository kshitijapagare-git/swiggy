package com.swiggy.ecomm.repository;

import com.swiggy.ecomm.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
