package org.ecommerce.notification.notification.order;

import org.ecommerce.notification.notification.payment.Currency;
import org.ecommerce.notification.notification.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderNotificationRequest(
        String trackingNumber,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Currency currency,
        CustomerResponseDTO customer,
        List<ProductPurchaseResponse> products
) {
}
