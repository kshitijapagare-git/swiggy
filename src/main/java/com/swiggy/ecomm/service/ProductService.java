package com.swiggy.ecomm.service;

import com.swiggy.ecomm.dto.ProductRequest;
import com.swiggy.ecomm.exception.ResourceNotFoundException;
import com.swiggy.ecomm.model.Product;
import com.swiggy.ecomm.repository.CartItemRepository;
import com.swiggy.ecomm.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public ProductService(ProductRepository productRepository, CartItemRepository cartItemRepository) {
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public Product create(ProductRequest request) {
        Product product = new Product();
        applyRequest(product, request);
        return productRepository.save(product);
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product update(Long id, ProductRequest request) {
        Product product = getById(id);
        applyRequest(product, request);
        return productRepository.save(product);
    }

    public void delete(Long id) {
        Product product = getById(id);
        cartItemRepository.deleteByProductId(id);
        productRepository.delete(product);
    }

    private void applyRequest(Product product, ProductRequest request) {
        product.setName(request.getName());
        product.setSku(request.getSku());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setStatus(request.getStatus());
    }
}
