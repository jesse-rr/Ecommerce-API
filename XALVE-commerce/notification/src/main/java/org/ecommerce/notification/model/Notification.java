package org.ecommerce.notification.model;

import lombok.*;
import org.ecommerce.notification.notification.order.OrderNotificationRequest;
import org.ecommerce.notification.notification.payment.PaymentNotificationRequest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {

    @Id
    private String notificationId;

    private NotificationType notificationType;
    private LocalDateTime timestamp;

    private PaymentNotificationRequest paymentNotificationRequest;
    private OrderNotificationRequest orderNotificationRequest;
}
