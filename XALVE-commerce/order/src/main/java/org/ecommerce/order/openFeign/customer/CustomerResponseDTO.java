package org.ecommerce.order.openFeign.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerResponseDTO(
        @NotNull @NotBlank
        String customerId,
        @NotNull @NotBlank
        String firstname,
        @NotNull @NotBlank
        String lastname,
        @NotNull @NotBlank
        String email
) {
}
