package com.jsl.order_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderConfig {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    NewTopic newTopic() {
        return TopicBuilder.name("orders").build();
    }
}
