package com.swiggy.ecomm.controller;

import com.swiggy.ecomm.dto.ProductTagRequest;
import com.swiggy.ecomm.dto.ProductTagResponse;
import com.swiggy.ecomm.service.ProductTagService;
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
@RequestMapping("/api/products/{productId}/tags")
public class ProductTagController {

    private final ProductTagService productTagService;

    public ProductTagController(ProductTagService productTagService) {
        this.productTagService = productTagService;
    }

    @PostMapping
    public ResponseEntity<ProductTagResponse> create(@PathVariable Long productId,
                                                       @Valid @RequestBody ProductTagRequest request) {
        ProductTagResponse response = ProductTagResponse.from(productTagService.create(productId, request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{tagId}")
    public ProductTagResponse getById(@PathVariable Long productId, @PathVariable Long tagId) {
        return ProductTagResponse.from(productTagService.getById(productId, tagId));
    }

    @GetMapping
    public List<ProductTagResponse> getAll(@PathVariable Long productId) {
        return productTagService.getAllForProduct(productId).stream().map(ProductTagResponse::from).toList();
    }

    @PutMapping("/{tagId}")
    public ProductTagResponse update(@PathVariable Long productId, @PathVariable Long tagId,
                                      @Valid @RequestBody ProductTagRequest request) {
        return ProductTagResponse.from(productTagService.update(productId, tagId, request));
    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<Void> delete(@PathVariable Long productId, @PathVariable Long tagId) {
        productTagService.delete(productId, tagId);
        return ResponseEntity.noContent().build();
    }
}
