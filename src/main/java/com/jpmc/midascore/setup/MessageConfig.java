package com.jpmc.midascore.setup;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class MessageConfig {

    @Bean
    public NewTopic userTopic(){
        return TopicBuilder.name("users").build();
    }

    @Bean
    public NewTopic transactionTopic(){
        return TopicBuilder.name("transactions").build();
    }
}