package com.jsl.payment_service.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaNotificationProducer {
    private final NewTopic newTopic;
    private final KafkaTemplate<String, PaymentNotificationRequest> paymentNotificationRequestKafkaTemplate;

    public void sendNotification(PaymentNotificationRequest paymentNotificationRequest) {
        Message<PaymentNotificationRequest> message = MessageBuilder
                .withPayload(paymentNotificationRequest)
                .setHeader(KafkaHeaders.TOPIC, newTopic.name())
                .build();

        paymentNotificationRequestKafkaTemplate.send(message);
    }
}
