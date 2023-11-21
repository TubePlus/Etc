package com.example.etc_service.alarm.application;

import com.example.etc_service.alarm.dto.AlarmDto;
import com.example.etc_service.config.kafka.dto.BoardDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SseService {
    SseEmitter subscribeWithUUID(String uuid);
    void sendBoardCreateAlarm(String uuid, AlarmDto alarmDto);
}
