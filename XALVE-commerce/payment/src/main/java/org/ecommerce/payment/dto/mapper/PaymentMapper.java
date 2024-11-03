package org.ecommerce.payment.dto.mapper;

import org.ecommerce.payment.dto.PaymentRequestDTO;
import org.ecommerce.payment.model.Payment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequestDTO request) {
        return Payment.builder()
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .currency(request.currency())
                .orderId(request.orderId())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
