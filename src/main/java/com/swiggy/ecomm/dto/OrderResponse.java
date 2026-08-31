package com.swiggy.ecomm.dto;

import com.swiggy.ecomm.model.Order;
import com.swiggy.ecomm.model.OrderStatus;

import java.util.List;

public class OrderResponse {

    private Long id;
    private String customerName;
    private List<OrderItemResponse> items;
    private OrderStatus status;

    public static OrderResponse from(Order order) {
        OrderResponse response = new OrderResponse();
        response.id = order.getId();
        response.customerName = order.getCustomerName();
        response.items = order.getItems().stream().map(OrderItemResponse::from).toList();
        response.status = order.getStatus();
        return response;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<OrderItemResponse> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
