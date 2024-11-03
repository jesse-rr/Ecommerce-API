package org.ecommerce.payment.notification;

import org.ecommerce.payment.model.Customer;
import org.ecommerce.payment.model.Currency;
import org.ecommerce.payment.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
        Long orderId,
        String trackingNumber,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Currency currency,
        Customer customer
) {
}
