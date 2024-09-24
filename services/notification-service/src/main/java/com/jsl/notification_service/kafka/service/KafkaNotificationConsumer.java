package com.jsl.notification_service.kafka.service;

import com.jsl.notification_service.kafka.order.OrderConfirmation;
import com.jsl.notification_service.kafka.payment.PaymentConfirmation;
import jakarta.mail.MessagingException;

public interface KafkaNotificationConsumer {
    void consumerPaymentSuccessNotifications(PaymentConfirmation paymentConfirmation) throws MessagingException;
    void consumerOrderConfirmationNotifications(OrderConfirmation orderConfirmation) throws MessagingException;
}
