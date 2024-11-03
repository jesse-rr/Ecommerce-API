package org.ecommerce.order.openFeign.discount;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record DiscountDTO(
        @NotNull
        Long productId,
        @NotNull @Positive
        double discountValue,
        @NotNull
        LocalDateTime expirationDate,
        @NotNull
        boolean isActive
) {
}
