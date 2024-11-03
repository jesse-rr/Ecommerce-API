package com.ecommerce.discount.repository;

import com.ecommerce.discount.model.Discount;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DiscountRepository extends MongoRepository<Discount, String> {

    Optional<Discount> findByProductId(Long productId);

    List<Discount> findAllByExpirationTimeBefore(LocalDateTime expirationTime);
}
