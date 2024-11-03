package org.ecommerce.order.openFeign.cart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

public record Items(
        @NotNull
        Long productId,
        @NotNull @Positive
        int quantity
) implements Serializable {
}
