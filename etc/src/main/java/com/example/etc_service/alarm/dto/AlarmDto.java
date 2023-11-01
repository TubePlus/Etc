package com.example.etc_service.alarm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AlarmDto {
    private String uuid;
    private String alarmTitle;
    private String alarmContent; // url
    private Boolean alarmCheck;
    private String authorUuid;
}
