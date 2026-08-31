package com.swiggy.ecomm.repository;

import com.swiggy.ecomm.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
