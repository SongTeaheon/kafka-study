package com.song.study.kafka.protobufschemeregistry.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRequestDto {
    private int id;
    private String name;
    private String address;
}
