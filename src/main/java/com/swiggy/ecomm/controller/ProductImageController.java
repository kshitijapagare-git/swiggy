package com.swiggy.ecomm.controller;

import com.swiggy.ecomm.dto.ProductImageRequest;
import com.swiggy.ecomm.dto.ProductImageResponse;
import com.swiggy.ecomm.service.ProductImageService;
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
@RequestMapping("/api/products/{productId}/images")
public class ProductImageController {

    private final ProductImageService productImageService;

    public ProductImageController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    @PostMapping
    public ResponseEntity<ProductImageResponse> create(@PathVariable Long productId,
                                                         @Valid @RequestBody ProductImageRequest request) {
        ProductImageResponse response = ProductImageResponse.from(productImageService.create(productId, request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{imageId}")
    public ProductImageResponse getById(@PathVariable Long productId, @PathVariable Long imageId) {
        return ProductImageResponse.from(productImageService.getById(productId, imageId));
    }

    @GetMapping
    public List<ProductImageResponse> getAll(@PathVariable Long productId) {
        return productImageService.getAllForProduct(productId).stream().map(ProductImageResponse::from).toList();
    }

    @PutMapping("/{imageId}")
    public ProductImageResponse update(@PathVariable Long productId, @PathVariable Long imageId,
                                        @Valid @RequestBody ProductImageRequest request) {
        return ProductImageResponse.from(productImageService.update(productId, imageId, request));
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> delete(@PathVariable Long productId, @PathVariable Long imageId) {
        productImageService.delete(productId, imageId);
        return ResponseEntity.noContent().build();
    }
}
