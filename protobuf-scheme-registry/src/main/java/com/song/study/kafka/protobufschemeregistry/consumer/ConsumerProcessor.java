package com.song.study.kafka.protobufschemeregistry.consumer;

import com.google.protobuf.Descriptors;
import com.google.protobuf.DynamicMessage;
import com.song.MyRecordProto;
import com.song.MyRecordProto.MyRecord;
import com.song.study.kafka.protobufschemeregistry.config.KafkaTopicNames;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ConsumerProcessor {

    @KafkaListener(topics = KafkaTopicNames.TEST_TOPIC)
    public void subscribe(ConsumerRecord<String, DynamicMessage> message) {
        log.info("subscribe");
        log.info("received a message. {}", message);
        log.info("received a message. {}", message.value());
        for (Descriptors.FieldDescriptor field : message.value().getAllFields().keySet()) {
            System.out.println(field.getName() + ": " + message.value().getField(field));
        }
    }
}
