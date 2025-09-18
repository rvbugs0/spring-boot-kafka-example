package com.bugs.studios.kakfa_consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication

public class KakfaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakfaConsumerApplication.class, args);
	}

}
