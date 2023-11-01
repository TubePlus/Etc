package com.example.etc_service.alarm.application;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SseService {
    SseEmitter subscribeWithUUID(String uuid);
    void sendMessageToUUID(String uuid, String message);
}
