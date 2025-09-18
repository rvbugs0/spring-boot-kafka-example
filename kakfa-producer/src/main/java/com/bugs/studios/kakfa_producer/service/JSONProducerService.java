package com.bugs.studios.kakfa_producer.service;


import com.common.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JSONProducerService {

    public static final Logger logger = LoggerFactory.getLogger(JSONProducerService.class);

    @Value("${kafka.users_topic}")
    private String usersTopic;

    @Autowired
    private KafkaTemplate<String, User> jsonKafkaTemplate;


    public void sendMessage(User data){
        logger.info(String.format("Message sent -> %s",data.toString()));
        Message<User> message = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC,usersTopic).build();
        jsonKafkaTemplate.send(message);
    }





}
