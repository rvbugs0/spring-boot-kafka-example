package com.bugs.studios.kakfa_consumer.service;



import com.common.model.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {


    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @KafkaListener(
           topics = "${kafka.movies_topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    ) // directly reference the property from application.properties
    public void consume(ConsumerRecord<String, String> record, Acknowledgment ack) throws Exception {

            String key = record.key();
            String value = record.value();
            int partition = record.partition();
            long offset = record.offset();
            logger.warn(String.format(
                    "Partition: %d, Offset: %d, Key: %s, Message: %s",
                    partition, offset, key, value
            ));

            //            Manually acknowledge after success - offset will move
            if(!value.equals("throw")){
                ack.acknowledge();
                System.out.println("Acknowledged");
//                otherwise check if it reaches DLT
//                not acknowledging will not send it to DLT, an exception thrown inside listener will send it to DLT since we
//                have provided the bean configuration for it.
//                if we don't acknowledge - kafka will keep sending from that offset infinite times until we finally acknowledge it.
            }else {
//                this will send it to DLT
                throw new Exception("message was throw");
            }
    }


    @KafkaListener(
            topics = "${kafka.users_topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    ) // directly reference the property from application.properties
    public void consumeUser(ConsumerRecord<String, User> record, Acknowledgment ack) {
        try{
            String key = record.key();
            User value = record.value();
            int partition = record.partition();
            long offset = record.offset();
            logger.warn(String.format(
                    "Partition: %d, Offset: %d, Key: %s, Message: %s",
                    partition, offset, key, value
            ));

            //            Manually acknowledge after success - offset will move
            ack.acknowledge();

        }
        catch (Exception e){
            // no acknowledgement - message will be redelivered.
            System.out.println(e.getMessage());
        }

    }


}
