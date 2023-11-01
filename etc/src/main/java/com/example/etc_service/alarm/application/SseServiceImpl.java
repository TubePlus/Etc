package com.example.etc_service.alarm.application;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SseServiceImpl implements SseService {
    private final Map<String, List<SseEmitter>> emittersByUUID = new ConcurrentHashMap<>();

    public SseEmitter subscribeWithUUID(String uuid) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE); // Optional: Set a long timeout or another desired timeout value

        emittersByUUID.computeIfAbsent(uuid, k -> new CopyOnWriteArrayList<>()).add(emitter);

        emitter.onCompletion(() -> removeEmitter(uuid, emitter));
        emitter.onTimeout(() -> removeEmitter(uuid, emitter));

        return emitter;
    }

    public void sendMessageToUUID(String uuid, String message) {
        List<SseEmitter> emitters = emittersByUUID.get(uuid);
        if (emitters != null) {
            List<SseEmitter> deadEmitters = new CopyOnWriteArrayList<>();
            for (SseEmitter emitter : emitters) {
                try {
                    emitter.send(message);
                } catch (IOException | IllegalStateException e) {
                    deadEmitters.add(emitter);
                }
            }
            emitters.removeAll(deadEmitters);
        }
    }

    private void removeEmitter(String uuid, SseEmitter emitter) {
        List<SseEmitter> emitters = emittersByUUID.get(uuid);
        if (emitters != null) {
            emitters.remove(emitter);
            if (emitters.isEmpty()) {
                emittersByUUID.remove(uuid);
            }
        }
    }
}
