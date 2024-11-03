package org.ecommerce.payment.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

public record Customer(
        @NotNull @NotBlank
        String firstname,
        @NotNull @NotBlank
        String lastname,
        @NotNull @NotBlank
        String email
) {
}
