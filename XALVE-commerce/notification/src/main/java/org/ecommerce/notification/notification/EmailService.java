package org.ecommerce.notification.notification;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.notification.model.NotificationType;
import org.ecommerce.notification.notification.order.OrderNotificationRequest;
import org.ecommerce.notification.notification.payment.PaymentNotificationRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;


@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Async
    public void sendEmailPaymentNotification(
            PaymentNotificationRequest request,
            LocalDateTime timestamp
    ) throws MessagingException, IOException {
        var customer = request.customer().firstname() + " " + request.customer().lastname();

        MimeMessage message = mailSender.createMimeMessage();
        message.setFrom(new InternetAddress("{BUSSINESS_EMAIl}"));
        message.setRecipients(MimeMessage.RecipientType.TO, request.customer().email());
        message.setSubject(customer);

        String template = Files.readString(Paths.get("{YOUR_EMAIL_TEMPLATE_EXAMPLE_IN_RESOURCES}"));
        template = template
                .replace("${customer}", customer)
                .replace("${timestamp}", String.valueOf(timestamp))
                .replace("${currency}", String.valueOf(request.currency()))
                .replace("${amount}", String.valueOf(request.amount()))
                .replace("${trackingNumber}", request.trackingNumber());

        message.setContent(template, "text/html; charset=utf-8");
        mailSender.send(message);
        log.info("SENDING PAYMENT EMAIL NOTIFICATION");
    }

    @Async
    public void sendEmailOrderNotification(
            OrderNotificationRequest request,
            LocalDateTime timestamp
    ) throws MessagingException, IOException {
        var customer = request.customer().firstname() + " " + request.customer().lastname();

        MimeMessage message = mailSender.createMimeMessage();
        message.setFrom(new InternetAddress("{BUSSINESS_EMAIl}"));
        message.setRecipients(MimeMessage.RecipientType.TO, request.customer().email());
        message.setSubject(customer);

        String template = Files.readString(Paths.get("{YOUR_EMAIL_TEMPLATE_EXAMPLE_IN_RESOURCES}"));
        template = template
                .replace("${customer}", customer)
                .replace("${timestamp}", String.valueOf(timestamp))
                .replace("${currency}", String.valueOf(request.currency()))
                .replace("${amount}", String.valueOf(request.totalAmount()))
                .replace("${trackingNumber}", request.trackingNumber());

        message.setContent(template, "text/html; charset=utf-8");
        mailSender.send(message);
        log.info("SENDING ORDER EMAIL NOTIFICATION");
    }
}
