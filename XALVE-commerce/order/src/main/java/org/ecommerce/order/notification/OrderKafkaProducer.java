package org.ecommerce.order.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class OrderKafkaProducer {

    private final KafkaTemplate<String, OrderNotificationRequest> kafkaTemplate;

    @Async
    public void sendOrderNotificationRequest(OrderNotificationRequest notification) {
        log.info("SENDING ORDER NOTIFICATION FROM ORDER_KAFKA_PRODUCER");
        Message<OrderNotificationRequest> message = MessageBuilder
                .withPayload(notification)
                .setHeader(KafkaHeaders.TOPIC, "order-topic")
                .build();
        kafkaTemplate.send(message);
    }
}
