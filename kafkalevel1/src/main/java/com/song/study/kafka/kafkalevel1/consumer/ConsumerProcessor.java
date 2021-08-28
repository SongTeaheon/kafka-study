package com.song.study.kafka.kafkalevel1.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Duration;
import java.util.Collections;

@Component
@RequiredArgsConstructor
@Slf4j
public class ConsumerProcessor {

    private final Consumer<String, String> consumer;

    @PostConstruct
    public void subscribe() {
        consumer.subscribe(Collections.singletonList("test-topic"));

        Thread th = new Thread(() -> {
            while (true) {
                consumer.poll(Duration.ofMillis(1000)).forEach(record -> log.info("received a message. {}", record));
                consumer.commitAsync();
            }
        });

        th.start();
    }
    @PreDestroy
    public void destroy() {
        consumer.close(Duration.ofSeconds(5));
    }
}
