package org.ecommerce.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.ecommerce.order.model.Order;

public record OrderLineDTO(
        @NotNull
        Order order,
        @NotNull
        Long productId,
        @NotNull @Positive
        int quantity
) {
}
