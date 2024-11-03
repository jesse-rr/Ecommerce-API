package org.ecommerce.order.openFeign.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(
        @NotNull
        Long productId,
        @NotNull @Positive
        int requestedQuantity
) {
}
