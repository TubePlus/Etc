package com.example.etc_service.alarm.application;

import com.example.etc_service.alarm.dto.AlarmDto;
import com.example.etc_service.config.kafka.dto.BoardCreateAlarmDto;
import com.example.etc_service.config.kafka.dto.BoardDto;

import java.util.List;

public interface AlarmService {
    void readAlarm(Long id);

    List<Integer> getAlarmList(String uuid);

    void readAllAlarm(List<Long> ids);

    AlarmDto createBoardCreateAlarm(String uuid, String authorUuid, BoardDto boardDto);
}
