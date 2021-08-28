package com.song.study.kafka.kafkalevel1.consumer;

import com.song.study.kafka.kafkalevel1.config.KafkaTopicNames;
import com.song.study.kafka.kafkalevel1.model.User;
import io.github.gaemi.model.Shipment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.reflect.ReflectData;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ConsumerProcessor {

    @KafkaListener(topics = KafkaTopicNames.TEST_TOPIC)
    public void subscribe(ConsumerRecord<String, GenericRecord> record) {

        User user = mapRecordToObject(record.value(), new User());

        log.info("received a message. {}", record.value());
        log.info("user. {}", user);
    }

    // https://karengryg.io/2018/08/25/avro-and-pojo-conversionstips-for-kafka-devs/
    private <T> T mapRecordToObject(GenericRecord record, T object) {
        final Schema schema = ReflectData.get().getSchema(object.getClass());

        record.getSchema().getFields().forEach(d -> PropertyAccessorFactory.forDirectFieldAccess(object).setPropertyValue(d.name(), record.get(d.name()) == null ? record.get(d.name()) : record.get(d.name()).toString()));
        return object;
    }
}
