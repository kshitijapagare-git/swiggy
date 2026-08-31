package com.swiggy.ecomm.controller;

import com.swiggy.ecomm.dto.CartItemRequest;
import com.swiggy.ecomm.dto.CartItemResponse;
import com.swiggy.ecomm.service.CartItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping
    public ResponseEntity<CartItemResponse> create(@Valid @RequestBody CartItemRequest request) {
        CartItemResponse response = CartItemResponse.from(cartItemService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public CartItemResponse getById(@PathVariable Long id) {
        return CartItemResponse.from(cartItemService.getById(id));
    }

    @GetMapping
    public List<CartItemResponse> getAll() {
        return cartItemService.getAll().stream().map(CartItemResponse::from).toList();
    }

    @PutMapping("/{id}")
    public CartItemResponse update(@PathVariable Long id, @Valid @RequestBody CartItemRequest request) {
        return CartItemResponse.from(cartItemService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cartItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
