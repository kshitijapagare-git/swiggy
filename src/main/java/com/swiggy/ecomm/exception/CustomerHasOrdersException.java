package com.swiggy.ecomm.exception;

public class CustomerHasOrdersException extends RuntimeException {

    public CustomerHasOrdersException(String message) {
        super(message);
    }
}
