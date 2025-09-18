package com.bugs.studios.kakfa_producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;



@Service
public class ProducerService {

    public static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

    @Value("${kafka.topic}")
    private String topic;


    private final KafkaTemplate<String,String> kafkaTemplate;

    public  ProducerService(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage(String key,String value){

//        to view messages use
//        .bin/kafka-console-consumer.sh --topic movies --from-beginning --bootstrap-server localhost:9092  --property print.key=true  --property print.partition=true  --property print.offset=true
        logger.info(String.format("Message sent: Key = %s, Value= %s",key,value));
        if(key!=null){
            if(key.toUpperCase().equals("HOLLYWOOD")){
                kafkaTemplate.send(topic,0,key,value);
                return;
            }
        }
        if(key!=null){
            kafkaTemplate.send(topic,1,key,value);
        }else{
            kafkaTemplate.send(topic,1,"GENERAL",value);
        }

    }



}
