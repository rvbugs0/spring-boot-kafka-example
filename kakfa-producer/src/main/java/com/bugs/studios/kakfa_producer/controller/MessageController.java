package com.bugs.studios.kakfa_producer.controller;

import com.bugs.studios.kakfa_producer.service.ProducerService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class MessageController {


    private final ProducerService producerService;

    public MessageController(ProducerService producerService){
        this.producerService = producerService;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam("key") String key,@RequestParam("value") String value){
        producerService.sendMessage(key,value);
        return ResponseEntity.ok("Message sent to the topic");
    }


}
