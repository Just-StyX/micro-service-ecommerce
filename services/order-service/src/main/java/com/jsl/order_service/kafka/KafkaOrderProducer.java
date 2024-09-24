package com.jsl.order_service.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaOrderProducer {
    private final NewTopic newTopic;
    private final KafkaTemplate<String, OrderConfirmation> orderConfirmationKafkaTemplate;

    public void sendOrderConfirmation(OrderConfirmation orderConfirmation) {
        Message<OrderConfirmation> orderConfirmationMessage = MessageBuilder
                .withPayload(orderConfirmation)
                .setHeader(KafkaHeaders.TOPIC, newTopic.name()).build();

        orderConfirmationKafkaTemplate.send(orderConfirmationMessage);
    }
}
