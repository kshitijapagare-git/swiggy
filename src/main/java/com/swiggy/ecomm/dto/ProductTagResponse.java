package com.swiggy.ecomm.dto;

import com.swiggy.ecomm.model.ProductTag;

import java.time.Instant;

public class ProductTagResponse {

    private Long id;
    private Long productId;
    private String key;
    private String value;
    private Instant createdAt;
    private Instant updatedAt;

    public static ProductTagResponse from(ProductTag productTag) {
        ProductTagResponse response = new ProductTagResponse();
        response.id = productTag.getId();
        response.productId = productTag.getProduct().getId();
        response.key = productTag.getKey();
        response.value = productTag.getValue();
        response.createdAt = productTag.getCreatedAt();
        response.updatedAt = productTag.getUpdatedAt();
        return response;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
