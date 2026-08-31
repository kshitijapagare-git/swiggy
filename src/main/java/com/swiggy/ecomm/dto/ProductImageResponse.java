package com.swiggy.ecomm.dto;

import com.swiggy.ecomm.model.ProductImage;

public class ProductImageResponse {

    private Long id;
    private Long productId;
    private String imageUrl;
    private int sortOrder;
    private boolean isPrimary;

    public static ProductImageResponse from(ProductImage productImage) {
        ProductImageResponse response = new ProductImageResponse();
        response.id = productImage.getId();
        response.productId = productImage.getProduct().getId();
        response.imageUrl = productImage.getImageUrl();
        response.sortOrder = productImage.getSortOrder();
        response.isPrimary = productImage.isPrimary();
        return response;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public boolean isPrimary() {
        return isPrimary;
    }
}
