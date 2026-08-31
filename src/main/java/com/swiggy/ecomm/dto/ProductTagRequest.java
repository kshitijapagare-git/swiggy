package com.swiggy.ecomm.dto;

import jakarta.validation.constraints.NotBlank;

public class ProductTagRequest {

    @NotBlank
    private String key;

    @NotBlank
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
