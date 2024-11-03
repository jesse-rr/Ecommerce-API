package org.ecommerce.notification.notification.payment;

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
