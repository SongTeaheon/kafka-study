package com.song.study.kafka.kafkacloudstream.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.function.Consumer;

@EnableKafka // need it for KafkaListener Annotation to find out Consumer Beans
@Configuration
@Slf4j
public class KafkaConsumerConfig {

    @Bean
    public Consumer<Object> consume() {
        return (o) -> log.info("consume 이벤트 수신" + o.toString());
    }
}
