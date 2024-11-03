package org.ecommerce.customer.dto;

public record CustomerResponseDTO(
        String customerId,
        String firstname,
        String lastname,
        String email
) {
}
