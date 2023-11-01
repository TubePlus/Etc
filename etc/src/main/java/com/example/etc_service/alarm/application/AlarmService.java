package com.example.etc_service.alarm.application;

import com.example.etc_service.alarm.dto.AlarmDto;

import java.util.List;

public interface AlarmService {
    void readAlarm(Long id);

    List<Integer> getAlarmList(String uuid);

    void readAllAlarm(List<Long> ids);
}
