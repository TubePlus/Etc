package com.example.etc_service.alarm.adapter.infrastructure.web.controller;

import com.example.etc_service.global.base.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/etc")
public class AlarmController {
    @GetMapping("/alarm")
//    gateway test용
    public ApiResponse<Object> readMyAlarm() {
        return ApiResponse.ofSuccess();
    }
}
