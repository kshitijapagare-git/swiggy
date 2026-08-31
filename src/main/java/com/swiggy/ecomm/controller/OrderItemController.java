package com.swiggy.ecomm.controller;

import com.swiggy.ecomm.dto.OrderItemRequest;
import com.swiggy.ecomm.dto.OrderItemResponse;
import com.swiggy.ecomm.service.OrderItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping("/api/orders/{orderId}/items")
    public ResponseEntity<OrderItemResponse> create(@PathVariable Long orderId, @Valid @RequestBody OrderItemRequest request) {
        OrderItemResponse response = OrderItemResponse.from(orderItemService.create(orderId, request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/api/order-items/{id}")
    public OrderItemResponse getById(@PathVariable Long id) {
        return OrderItemResponse.from(orderItemService.getById(id));
    }

    @GetMapping("/api/order-items")
    public List<OrderItemResponse> getAll() {
        return orderItemService.getAll().stream().map(OrderItemResponse::from).toList();
    }

    @PutMapping("/api/order-items/{id}")
    public OrderItemResponse update(@PathVariable Long id, @Valid @RequestBody OrderItemRequest request) {
        return OrderItemResponse.from(orderItemService.update(id, request));
    }

    @DeleteMapping("/api/order-items/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
