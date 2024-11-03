package org.ecommerce.order.openFeign.payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.ecommerce.order.model.PaymentMethod;
import org.ecommerce.order.openFeign.customer.CustomerResponseDTO;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

public record PaymentRequestDTO(
        @NotNull
        Long orderId,
        @NotNull @NotBlank
        String trackingNumber,
        @NotNull @Positive
        BigDecimal amount,
        @NotNull
        PaymentMethod paymentMethod,
        @NotNull
        Currency currency,
        @Validated
        CustomerResponseDTO customer
) {
}
