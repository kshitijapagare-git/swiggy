package com.swiggy.ecomm.dto;

import com.swiggy.ecomm.model.Customer;

import java.time.Instant;
import java.util.UUID;

public class CustomerResponse {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Instant createdAt;

    public static CustomerResponse from(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        response.id = customer.getId();
        response.firstName = customer.getFirstName();
        response.lastName = customer.getLastName();
        response.email = customer.getEmail();
        response.phone = customer.getPhone();
        response.address = customer.getAddress();
        response.createdAt = customer.getCreatedAt();
        return response;
    }

    public UUID getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
