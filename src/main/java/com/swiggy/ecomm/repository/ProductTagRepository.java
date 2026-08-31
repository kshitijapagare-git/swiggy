package com.swiggy.ecomm.repository;

import com.swiggy.ecomm.model.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductTagRepository extends JpaRepository<ProductTag, Long> {

    List<ProductTag> findByProductId(Long productId);

    Optional<ProductTag> findByIdAndProductId(Long id, Long productId);
}
