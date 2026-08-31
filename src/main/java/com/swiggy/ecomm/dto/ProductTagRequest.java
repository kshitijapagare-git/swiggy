package com.swiggy.ecomm.dto;

import jakarta.validation.constraints.NotBlank;

public class ProductTagRequest {

    @NotBlank
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
