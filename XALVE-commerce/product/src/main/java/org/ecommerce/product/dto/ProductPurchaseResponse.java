package org.ecommerce.product.dto;

import org.ecommerce.product.model.ProductStatus;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
        Long productId,
        String name,
        String description,
        BigDecimal price,
        int quantity,
        ProductStatus status
) {
}
