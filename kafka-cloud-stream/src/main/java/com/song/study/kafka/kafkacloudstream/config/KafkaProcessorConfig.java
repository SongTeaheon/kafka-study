package com.song.study.kafka.kafkacloudstream.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@Slf4j
public class KafkaProcessorConfig {

    @Bean
    public Function<String, String> process() {
        return (value) -> {
            log.info("process : " + value);
            return value.toUpperCase();
        };
    }
}
