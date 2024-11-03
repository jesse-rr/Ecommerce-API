package org.ecommerce.order.dto.mapper;

import org.ecommerce.order.dto.OrderRequestDTO;
import org.ecommerce.order.model.Order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequestDTO request, BigDecimal totalAmount, String trackingNumber, String customerId) {
        return Order.builder()
                .paymentMethod(request.paymentMethod())
                .totalAmount(totalAmount)
                .trackingNumber(trackingNumber)
                .customerId(customerId)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
