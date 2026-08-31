package com.swiggy.ecomm.dto;

import com.swiggy.ecomm.model.CartItem;

public class CartItemResponse {

    private Long id;
    private Long cartId;
    private Long productId;
    private Integer quantity;

    public static CartItemResponse from(CartItem item) {
        CartItemResponse response = new CartItemResponse();
        response.id = item.getId();
        response.cartId = item.getCart().getId();
        response.productId = item.getProduct().getId();
        response.quantity = item.getQuantity();
        return response;
    }

    public Long getId() {
        return id;
    }

    public Long getCartId() {
        return cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
