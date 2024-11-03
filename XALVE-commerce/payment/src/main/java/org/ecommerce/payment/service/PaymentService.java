package org.ecommerce.payment.service;

import lombok.RequiredArgsConstructor;
import org.ecommerce.payment.dto.PaymentRequestDTO;
import org.ecommerce.payment.dto.mapper.PaymentMapper;
import org.ecommerce.payment.notification.PaymentKafkaProducer;
import org.ecommerce.payment.notification.PaymentNotificationRequest;
import org.ecommerce.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final PaymentKafkaProducer kafkaProducer;

    public Long addPayment(PaymentRequestDTO request) {
        var payment = repository.save(mapper.toPayment(request));

        kafkaProducer.sendPaymentNotificationRequest(
                new PaymentNotificationRequest(
                        request.orderId(),
                        request.trackingNumber(),
                        request.amount(),
                        request.paymentMethod(),
                        request.currency(),
                        request.customer()
                )
        );
        return payment.getPaymentId();
    }
}
