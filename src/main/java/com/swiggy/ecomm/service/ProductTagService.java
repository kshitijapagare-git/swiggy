package com.swiggy.ecomm.service;

import com.swiggy.ecomm.dto.ProductTagRequest;
import com.swiggy.ecomm.exception.ResourceNotFoundException;
import com.swiggy.ecomm.model.Product;
import com.swiggy.ecomm.model.ProductTag;
import com.swiggy.ecomm.repository.ProductRepository;
import com.swiggy.ecomm.repository.ProductTagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTagService {

    private final ProductRepository productRepository;
    private final ProductTagRepository productTagRepository;

    public ProductTagService(ProductRepository productRepository, ProductTagRepository productTagRepository) {
        this.productRepository = productRepository;
        this.productTagRepository = productTagRepository;
    }

    public ProductTag create(Long productId, ProductTagRequest request) {
        Product product = getProduct(productId);
        ProductTag productTag = new ProductTag();
        productTag.setProduct(product);
        productTag.setTag(request.getTag());
        return productTagRepository.save(productTag);
    }

    public ProductTag getById(Long productId, Long tagId) {
        return productTagRepository.findByIdAndProductId(tagId, productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product tag not found with id " + tagId + " for product " + productId));
    }

    public List<ProductTag> getAllForProduct(Long productId) {
        getProduct(productId);
        return productTagRepository.findByProductId(productId);
    }

    public ProductTag update(Long productId, Long tagId, ProductTagRequest request) {
        ProductTag productTag = getById(productId, tagId);
        productTag.setTag(request.getTag());
        return productTagRepository.save(productTag);
    }

    public void delete(Long productId, Long tagId) {
        ProductTag productTag = getById(productId, tagId);
        productTagRepository.delete(productTag);
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));
    }
}
