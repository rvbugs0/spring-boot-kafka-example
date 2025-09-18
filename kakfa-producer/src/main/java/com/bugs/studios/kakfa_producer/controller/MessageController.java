package com.bugs.studios.kakfa_producer.controller;

import com.bugs.studios.kakfa_producer.service.JSONProducerService;
import com.bugs.studios.kakfa_producer.service.ProducerService;
import com.common.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class MessageController {

    @Autowired
    private  ProducerService producerService;

    @Autowired
    private JSONProducerService jsonProducerService;


    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam("key") String key,@RequestParam("value") String value){
        producerService.sendMessage(key,value);
        return ResponseEntity.ok("Message sent to the topic");
    }

    @PostMapping("/jsonPublish")
    public ResponseEntity<String> publishJson(@RequestBody User user) throws JsonProcessingException {
        jsonProducerService.sendMessage(user);
        return ResponseEntity.ok("JSON Message sent to the topic");
    }


}
