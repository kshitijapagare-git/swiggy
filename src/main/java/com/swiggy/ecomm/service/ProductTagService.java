package com.swiggy.ecomm.service;

import com.swiggy.ecomm.dto.ProductTagRequest;
import com.swiggy.ecomm.exception.DuplicateResourceException;
import com.swiggy.ecomm.exception.ResourceNotFoundException;
import com.swiggy.ecomm.model.Product;
import com.swiggy.ecomm.model.ProductTag;
import com.swiggy.ecomm.repository.ProductRepository;
import com.swiggy.ecomm.repository.ProductTagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductTagService {

    private final ProductTagRepository productTagRepository;
    private final ProductRepository productRepository;

    public ProductTagService(ProductTagRepository productTagRepository, ProductRepository productRepository) {
        this.productTagRepository = productTagRepository;
        this.productRepository = productRepository;
    }

    public ProductTag create(Long productId, ProductTagRequest request) {
        Product product = getProduct(productId);

        String key = request.getKey().trim();
        String value = request.getValue().trim();

        if (productTagRepository.existsByProductIdAndKeyAndValue(productId, key, value)) {
            throw new DuplicateResourceException(
                    "Tag with key '" + key + "' and value '" + value + "' already exists for product " + productId);
        }

        ProductTag productTag = new ProductTag();
        productTag.setProduct(product);
        productTag.setKey(key);
        productTag.setValue(value);
        return productTagRepository.save(productTag);
    }

    public ProductTag getById(Long productId, Long tagId) {
        getProduct(productId);
        return productTagRepository.findByIdAndProductId(tagId, productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "ProductTag not found with id " + tagId + " for product " + productId));
    }

    public List<ProductTag> listByProduct(Long productId) {
        getProduct(productId);
        return productTagRepository.findByProductId(productId);
    }

    public List<ProductTag> replaceAll(Long productId, List<ProductTagRequest> requests) {
        Product product = getProduct(productId);

        productTagRepository.deleteByProductId(productId);

        Set<String> seen = new HashSet<>();
        List<ProductTag> tags = new ArrayList<>();
        for (ProductTagRequest request : requests) {
            String key = request.getKey().trim();
            String value = request.getValue().trim();
            String dedupeKey = key + "\u0000" + value;

            if (!seen.add(dedupeKey)) {
                throw new DuplicateResourceException(
                        "Duplicate tag with key '" + key + "' and value '" + value + "' in request for product " + productId);
            }

            ProductTag productTag = new ProductTag();
            productTag.setProduct(product);
            productTag.setKey(key);
            productTag.setValue(value);
            tags.add(productTag);
        }

        return productTagRepository.saveAll(tags);
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
