package com.bugs.studios.kakfa_producer.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {


    @Value("${kafka.topic}")
    private String topic;

    @Bean
    public NewTopic moviesTopic(){
        return TopicBuilder.name(topic).partitions(2).build();
    }

}
