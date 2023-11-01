package com.example.etc_service.alarm.vo;

import lombok.*;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AlarmAllRequest {
    private List<Long> id;
}
