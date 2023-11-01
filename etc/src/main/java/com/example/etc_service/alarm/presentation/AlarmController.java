package com.example.etc_service.alarm.presentation;

import com.example.etc_service.alarm.application.AlarmService;
import com.example.etc_service.alarm.dto.AlarmDto;
import com.example.etc_service.alarm.vo.AlarmAllRequest;
import com.example.etc_service.global.base.ApiResponse;
import com.example.etc_service.alarm.vo.SseRequest;
import com.example.etc_service.alarm.application.SseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/etc")
public class AlarmController {
    private final SseService sseService;
    private final AlarmService alarmService;
    @Operation(summary = "uuid로 알람 구독", description = "uuid를 통해 자신의 알람을 listen상태로 둔다.")
    @PostMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ApiResponse<?> getAlarm(
            @RequestBody SseRequest sseRequest
            ) {
        sseService.subscribeWithUUID(sseRequest.getUuid());
        return ApiResponse.ofSuccess("message connection success");
    }
    @Operation(summary = "uuid로 알람리스트 받기", description = "uuid에 맞는 알람 중 읽지않은것을 가져온다.")
    @GetMapping("/alarms")
    public ApiResponse<?> getAlarmList(
            @RequestBody SseRequest sseRequest
    ) {
        List<Integer> alarmIdList = alarmService.getAlarmList(sseRequest.getUuid());
        return ApiResponse.ofSuccess(alarmIdList);
    }
    @Operation(summary = "단일 알람 읽기", description = "알람을 하나 읽는다.")
    @PutMapping("/alarms/{id}")
    public ApiResponse<?> readAlarm(@PathVariable Long id) {
        alarmService.readAlarm(id);
        return ApiResponse.ofSuccess("read alarm success");
    }
    @Operation(summary = "단일 전체 읽기", description = "알람을 하나 읽는다.")
    @PutMapping("/alarms/read-all")
    public ApiResponse<?> readAllAlarm(@RequestBody AlarmAllRequest alarmAllRequest) {
        alarmService.readAllAlarm(alarmAllRequest.getId());
        return ApiResponse.ofSuccess("read alarm all success");
    }
}
