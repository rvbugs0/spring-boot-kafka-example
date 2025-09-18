package com.bugs.studios.kakfa_consumer.service;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {


    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @KafkaListener(
           topics = "${kafka.topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    ) // directly reference the property from application.properties
    public void consume(ConsumerRecord<String, String> record) {
        String key = record.key();
        String value = record.value();
        int partition = record.partition();
        long offset = record.offset();

        logger.warn(String.format(
                "Partition: %d, Offset: %d, Key: %s, Message: %s",
                partition, offset, key, value
        ));
    }




//     @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
//        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key


}
