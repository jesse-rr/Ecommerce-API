package org.ecommerce.payment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.ecommerce.payment.model.Currency;
import org.ecommerce.payment.model.Customer;
import org.ecommerce.payment.model.PaymentMethod;

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
        @NotNull
        Customer customer
) {
}
