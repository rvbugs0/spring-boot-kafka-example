package com.bugs.studios.kakfa_consumer;

import org.apache.kafka.common.TopicPartition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@SpringBootApplication

public class KakfaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakfaConsumerApplication.class, args);
	}


	@Bean
	public DefaultErrorHandler errorHandler(KafkaTemplate<Object, Object> template) {
		// Retry 3 times with 2 sec backoff
		FixedBackOff backOff = new FixedBackOff(2000L, 3L);

		// Send to DLT after retries are exhausted
		DeadLetterPublishingRecoverer recoverer =
				new DeadLetterPublishingRecoverer(template, (r, e) ->
				{
					System.out.println("INVALID MSG - SENDING TO DLT: "+r.topic()+".DLT , PARTITION: "+r.partition());
					return new TopicPartition(r.topic() + ".DLT", r.partition());
				}
				);
		return new DefaultErrorHandler(recoverer, backOff);
	}


}
