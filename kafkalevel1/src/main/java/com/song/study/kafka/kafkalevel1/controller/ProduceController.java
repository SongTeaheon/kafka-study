package com.song.study.kafka.kafkalevel1.controller;

import com.song.study.kafka.kafkalevel1.config.KafkaTopicNames;
import io.github.gaemi.model.Shipment;
import io.karengryg.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProduceController {

    private final KafkaTemplate<String, Object> template;

    @PostMapping("/produce")
    public void send(@RequestBody User message) {
        System.out.println("message = " + message);
        template.send(KafkaTopicNames.TEST_TOPIC, message);
    }
}
