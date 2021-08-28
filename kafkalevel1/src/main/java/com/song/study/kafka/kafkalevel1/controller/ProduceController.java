package com.song.study.kafka.kafkalevel1.controller;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProduceController {

    private final Producer<String, String> producer;

    public ProduceController(Producer<String, String> producer) {
        this.producer = producer;
    }

    @PostMapping("/produce")
    public void send(@RequestParam String message) {
        System.out.println("message = " + message);
        producer.send(new ProducerRecord<>("test-topic", message));
    }
}
