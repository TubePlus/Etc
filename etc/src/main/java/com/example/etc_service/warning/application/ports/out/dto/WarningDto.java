package com.example.etc_service.warning.application.ports.out.dto;

import com.example.etc_service.warning.domain.Warning;
import com.example.etc_service.warning.domain.WarningType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class WarningDto {
//    UseCase에서 데이터를 dto로 바꿔주는 곳
    private Long warningProvider;
    private String warnerUuid; // nullable
    private WarningType warningType;
    private String warningTitle;
    private String warningContent;
    private String warningUrl;
    private Long warnerInt; // nullable

    public static WarningDto formWarning(Warning warning) {
        return WarningDto.builder()
                .warningProvider(warning.getWarningProvider())
                .warnerUuid(warning.getWarnerUuid())
                .warningType(warning.getWarningType())
                .warningTitle(warning.getWarningTitle())
                .warningContent(warning.getWarningContent())
                .warningUrl(warning.getWarningUrl())
                .warnerInt(warning.getWarnerInt())
                .build();
    }
}
