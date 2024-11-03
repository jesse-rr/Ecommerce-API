package org.ecommerce.notification.notification.order;

public record CustomerResponseDTO(
        String customerId,
        String firstname,
        String lastname,
        String email
) {
}
