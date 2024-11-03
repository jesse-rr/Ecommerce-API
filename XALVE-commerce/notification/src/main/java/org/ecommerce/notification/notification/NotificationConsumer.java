package org.ecommerce.notification.notification;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.notification.model.Notification;
import org.ecommerce.notification.model.NotificationType;
import org.ecommerce.notification.notification.order.OrderNotificationRequest;
import org.ecommerce.notification.notification.payment.PaymentNotificationRequest;
import org.ecommerce.notification.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final EmailService emailService;
    private final NotificationRepository repository;

    @KafkaListener(topics = "payment-topic", groupId = "payment-group")
    public void consumerPaymentTopicNotification(PaymentNotificationRequest request) throws MessagingException, IOException {
        log.info("CONSUMING PAYMENT NOTIFICATION IN NOTIFICATION_CONSUMER");
        var notification = repository.save(
                Notification.builder()
                        .notificationType(NotificationType.PAYMENT_NOTIFICATION)
                        .paymentNotificationRequest(request)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
        emailService.sendEmailPaymentNotification(
                request,
                notification.getTimestamp()
        );
    }

    @KafkaListener(topics = "order-topic", groupId = "order-group")
    public void consumerOrderTopicNotification(OrderNotificationRequest request) throws MessagingException, IOException {
        log.info("CONSUMING ORDER NOTIFICATION IN NOTIFICATION_CONSUMER");
        var notification = repository.save(
                Notification.builder()
                        .notificationType(NotificationType.ORDER_NOTIFICATION)
                        .orderNotificationRequest(request)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
        emailService.sendEmailOrderNotification(
                request,
                notification.getTimestamp()
        );
    }
}
