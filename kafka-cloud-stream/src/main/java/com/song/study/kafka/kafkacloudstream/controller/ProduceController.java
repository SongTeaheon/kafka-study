package com.song.study.kafka.kafkacloudstream.controller;

import com.song.study.kafka.kafkacloudstream.config.KafkaTopicNames;
import com.song.study.kafka.kafkacloudstream.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;
import java.util.function.Supplier;

@RestController
@RequiredArgsConstructor
public class ProduceController {

    private final KafkaTemplate<String, Object> template;

    @PostMapping("/produce")
    public void send(@RequestBody User message) {
        System.out.println("message = " + message);
        template.send(KafkaTopicNames.TEST_TOPIC1, message.toString());
    }
}
