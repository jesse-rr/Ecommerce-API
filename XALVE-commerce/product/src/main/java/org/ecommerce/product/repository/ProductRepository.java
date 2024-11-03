package org.ecommerce.product.repository;

import org.ecommerce.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductsByProductIdInOrderByProductId(List<Long> ids);

}
