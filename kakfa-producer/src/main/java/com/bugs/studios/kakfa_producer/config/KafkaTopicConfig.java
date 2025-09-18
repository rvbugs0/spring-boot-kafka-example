package com.bugs.studios.kakfa_producer.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {


    @Value("${kafka.movies_topic}")
    private String moviesTopic;

    @Value("${kafka.users_topic}")
    private String usersTopic;

    @Bean
    public NewTopic moviesTopic(){
        return TopicBuilder.name(moviesTopic).partitions(2).build();
    }

    @Bean
    public NewTopic usersTopic(){
        return TopicBuilder.name(usersTopic).build();
    }

}
