package com.jsl.payment_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class PaymentConfig {
    @Bean
    public NewTopic paymentTopic() {
        return TopicBuilder
                .name("payments")
                .build();
    }
}
