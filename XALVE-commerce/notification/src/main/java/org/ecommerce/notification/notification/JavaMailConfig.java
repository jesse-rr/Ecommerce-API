package org.ecommerce.notification.notification;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class JavaMailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setPort(587);
        javaMailSender.setPassword("{BUSSINESS_PASSWORD_GMAIL}");
        javaMailSender.setUsername("{BUSSINESS_EMAIl}");
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setProtocol("smtp");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        javaMailSender.setJavaMailProperties(props);
        return javaMailSender;
    }
}
