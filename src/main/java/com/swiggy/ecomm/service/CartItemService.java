package com.swiggy.ecomm.service;

import com.swiggy.ecomm.dto.CartItemRequest;
import com.swiggy.ecomm.exception.ResourceNotFoundException;
import com.swiggy.ecomm.model.Cart;
import com.swiggy.ecomm.model.CartItem;
import com.swiggy.ecomm.model.Product;
import com.swiggy.ecomm.repository.CartItemRepository;
import com.swiggy.ecomm.repository.CartRepository;
import com.swiggy.ecomm.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartItemService(CartItemRepository cartItemRepository, CartRepository cartRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public CartItem create(CartItemRequest request) {
        Cart cart = cartRepository.findById(request.getCartId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id " + request.getCartId()));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + request.getProductId()));

        CartItem item = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId())
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setCart(cart);
                    newItem.setProduct(product);
                    return newItem;
                });

        item.setQuantity(request.getQuantity());
        return cartItemRepository.save(item);
    }

    public CartItem getById(Long id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found with id " + id));
    }

    public List<CartItem> getAll() {
        return cartItemRepository.findAll();
    }

    public CartItem update(Long id, CartItemRequest request) {
        CartItem item = getById(id);
        Cart cart = cartRepository.findById(request.getCartId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id " + request.getCartId()));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + request.getProductId()));
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(request.getQuantity());
        return cartItemRepository.save(item);
    }

    public void delete(Long id) {
        CartItem item = getById(id);
        cartItemRepository.delete(item);
    }
}
