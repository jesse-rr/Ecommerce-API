package org.ecommerce.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequestDTO(
        @NotNull @NotBlank
        String firstname,
        @NotNull @NotBlank
        String lastname,
        @NotNull @NotBlank @Email
        String email,
        @NotNull @NotBlank
        String password,
        @NotNull @NotBlank
        String cpfCnpj,
        @NotNull @NotBlank
        String phone,
        @NotNull @NotBlank
        String address
) {
}
