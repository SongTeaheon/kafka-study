package com.song.study.kafka.protobufschemeregistry.consumer;

import com.song.study.kafka.protobufschemeregistry.config.KafkaTopicNames;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ConsumerProcessor {

    @KafkaListener(topics = KafkaTopicNames.TEST_TOPIC)
    public void subscribe(Object message) {
        log.info("received a message. {}", message);
    }
}
