package com.swiggy.ecomm.repository;

import com.swiggy.ecomm.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByPhone(String phone);

    Page<Customer> findByEmail(String email, Pageable pageable);
}
