package com.swiggy.ecomm.service;

import com.swiggy.ecomm.dto.ProductImageRequest;
import com.swiggy.ecomm.exception.ResourceNotFoundException;
import com.swiggy.ecomm.model.Product;
import com.swiggy.ecomm.model.ProductImage;
import com.swiggy.ecomm.repository.ProductImageRepository;
import com.swiggy.ecomm.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductImageService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public ProductImageService(ProductRepository productRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }

    public ProductImage create(Long productId, ProductImageRequest request) {
        Product product = getProduct(productId);
        ProductImage productImage = new ProductImage();
        productImage.setProduct(product);
        productImage.setImageUrl(request.getImageUrl());
        productImage.setSortOrder(request.getSortOrder());
        productImage.setPrimary(request.isPrimary());
        return productImageRepository.save(productImage);
    }

    public ProductImage getById(Long productId, Long imageId) {
        return productImageRepository.findByIdAndProductIdAndDeletedAtIsNull(imageId, productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product image not found with id " + imageId + " for product " + productId));
    }

    public List<ProductImage> getAllForProduct(Long productId) {
        getProduct(productId);
        return productImageRepository.findByProductIdAndDeletedAtIsNull(productId);
    }

    public ProductImage update(Long productId, Long imageId, ProductImageRequest request) {
        ProductImage productImage = getById(productId, imageId);
        productImage.setImageUrl(request.getImageUrl());
        productImage.setSortOrder(request.getSortOrder());
        productImage.setPrimary(request.isPrimary());
        return productImageRepository.save(productImage);
    }

    public void delete(Long productId, Long imageId) {
        ProductImage productImage = getById(productId, imageId);
        productImage.setDeletedAt(LocalDateTime.now());
        productImageRepository.save(productImage);
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));
    }
}
