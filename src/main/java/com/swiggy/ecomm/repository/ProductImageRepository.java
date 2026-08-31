package com.swiggy.ecomm.repository;

import com.swiggy.ecomm.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findByProductIdAndDeletedAtIsNull(Long productId);

    Optional<ProductImage> findByIdAndProductIdAndDeletedAtIsNull(Long id, Long productId);
}
