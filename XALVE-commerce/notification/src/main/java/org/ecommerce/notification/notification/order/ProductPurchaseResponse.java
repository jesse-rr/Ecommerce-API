package org.ecommerce.notification.notification.order;


import java.math.BigDecimal;

public record ProductPurchaseResponse(
        Long productId,
        String name,
        String description,
        BigDecimal price,
        ProductStatus status,
        int quantity
) {
}
