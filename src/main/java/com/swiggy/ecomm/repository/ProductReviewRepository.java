package com.swiggy.ecomm.repository;

import com.swiggy.ecomm.model.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

    List<ProductReview> findByOrderIdAndProductId(Long orderId, Long productId);

    Optional<ProductReview> findByIdAndOrderIdAndProductId(Long id, Long orderId, Long productId);
}
