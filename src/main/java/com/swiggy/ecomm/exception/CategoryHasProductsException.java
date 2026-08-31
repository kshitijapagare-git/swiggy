package com.swiggy.ecomm.exception;

public class CategoryHasProductsException extends RuntimeException {

    public CategoryHasProductsException(String message) {
        super(message);
    }
}
