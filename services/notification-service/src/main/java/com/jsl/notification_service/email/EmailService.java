package com.jsl.notification_service.email;

import com.jsl.notification_service.kafka.order.Product;
import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Async;

import java.math.BigDecimal;
import java.util.List;

public interface EmailService {
    @Async
    void sendPaymentSuccessEmail(String destinationEmail, String customerName, BigDecimal amount, String orderReference) throws MessagingException;

    @Async
    void sendOrderConfirmationEmail(String destinationEmail, String customerName, BigDecimal amount, String orderReference, List<Product> products) throws MessagingException;
}
