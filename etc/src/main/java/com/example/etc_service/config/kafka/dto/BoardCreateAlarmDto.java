package com.example.etc_service.config.kafka.dto;

import lombok.Getter;

import java.util.List;
@Getter
public class BoardCreateAlarmDto {
    BoardDto board;
    String authorUuid;
    List<String> members;
}
