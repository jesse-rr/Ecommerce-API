package org.ecommerce.order.notification;

import org.ecommerce.order.model.PaymentMethod;
import org.ecommerce.order.openFeign.customer.CustomerResponseDTO;
import org.ecommerce.order.openFeign.payment.Currency;
import org.ecommerce.order.openFeign.product.ProductPurchaseResponse;

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
