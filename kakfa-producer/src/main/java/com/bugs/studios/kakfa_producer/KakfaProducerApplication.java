package com.bugs.studios.kakfa_producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication

public class KakfaProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakfaProducerApplication.class, args);
	}

}
