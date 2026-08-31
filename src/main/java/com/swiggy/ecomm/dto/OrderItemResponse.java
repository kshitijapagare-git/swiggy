package com.swiggy.ecomm.dto;

import com.swiggy.ecomm.model.OrderItem;

import java.math.BigDecimal;

public class OrderItemResponse {

    private Long id;
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private BigDecimal unitPrice;

    public static OrderItemResponse from(OrderItem item) {
        OrderItemResponse response = new OrderItemResponse();
        response.id = item.getId();
        response.orderId = item.getOrder().getId();
        response.productId = item.getProduct().getId();
        response.quantity = item.getQuantity();
        response.unitPrice = item.getUnitPrice();
        return response;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
}
