package com.swiggy.ecomm.dto;

import com.swiggy.ecomm.model.ProductTag;

public class ProductTagResponse {

    private Long id;
    private Long productId;
    private String tag;

    public static ProductTagResponse from(ProductTag productTag) {
        ProductTagResponse response = new ProductTagResponse();
        response.id = productTag.getId();
        response.productId = productTag.getProduct().getId();
        response.tag = productTag.getTag();
        return response;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public String getTag() {
        return tag;
    }
}
