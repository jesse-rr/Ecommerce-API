package org.ecommerce.order.openFeign.payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Customer(
        @NotNull @NotBlank
        String firstname,
        @NotNull @NotBlank
        String lastname,
        @NotNull @NotBlank
        String email
) {
}
