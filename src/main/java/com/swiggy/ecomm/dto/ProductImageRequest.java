package com.swiggy.ecomm.dto;

import jakarta.validation.constraints.NotBlank;

public class ProductImageRequest {

    @NotBlank
    private String imageUrl;

    private int sortOrder = 0;

    private boolean isPrimary = false;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }
}
