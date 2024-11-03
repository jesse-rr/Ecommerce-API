package org.ecommerce.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.ecommerce.order.model.PaymentMethod;
import org.ecommerce.order.openFeign.payment.Currency;
import org.ecommerce.order.openFeign.product.ProductPurchaseRequest;

import java.util.List;

public record OrderRequestDTO(
        @NotNull @NotBlank
        String cpfCpnj,
        @NotNull
        PaymentMethod paymentMethod,
        @NotNull
        Currency currency,
        List<ProductPurchaseRequest> products,
        @NotNull
        boolean isFromCart
) {
}
