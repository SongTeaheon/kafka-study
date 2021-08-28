package com.song.study.kafka.protobufschemeregistry.controller;

import com.song.MyRecordProto;
import com.song.study.kafka.protobufschemeregistry.config.KafkaTopicNames;
import com.song.study.kafka.protobufschemeregistry.controller.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProduceController {



    private final KafkaTemplate<String, MyRecordProto.MyRecord> template;

    @PostMapping("/produce")
    public void send(@RequestBody UserRequestDto userRequestDto) {
//        System.out.println("userRequestDto = " + userRequestDto);
//        SampleProto.User userProto = SampleProto.User.newBuilder()
//                .setId(userRequestDto.getId())
//                .setName(userRequestDto.getName())
//                .setAddress(userRequestDto.getAddress())
//                .addPhoneNumbers(SampleProto.User.PhoneNumber.newBuilder().setNumber("01032281298").setType(SampleProto.User.PhoneType.MOBILE).build())
//                .build();
        MyRecordProto.MyRecord myRecord = MyRecordProto.MyRecord.newBuilder().setF1(userRequestDto.getName()).build();
        System.out.println("userProto = " + myRecord);

        template.send(KafkaTopicNames.TEST_TOPIC, "key", myRecord);
    }
}
