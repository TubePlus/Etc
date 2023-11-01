package com.example.etc_service.alarm.vo;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SseRequest {
    @NotNull
    private String uuid;
}
