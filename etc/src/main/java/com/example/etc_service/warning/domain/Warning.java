package com.example.etc_service.warning.domain;

import com.example.etc_service.warning.adapter.infrastructure.mysql.entity.WarningEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Warning {

    private Long id;
    private Long warningProvider;
    private String warnerUuid; // nullable
    private WarningType warningType;
    private String warningTitle;
    private String warningContent;
    private String warningUrl;
    private Long warnerInt; // nullable

//
    public static Warning saveWarning(
            Long warningProvider, String warnerUuid, WarningType warningType,
            String  warningTitle, String warningContent, String warningUrl, Long warnerInt
    ) {
        return Warning.builder()
                .warningProvider(warningProvider)
                .warnerUuid(warnerUuid)
                .warningType(warningType)
                .warningTitle(warningTitle)
                .warningContent(warningContent)
                .warningUrl(warningUrl)
                .warnerInt(warnerInt)
                .build();
    }

//     entity form 저장 로직
    public static Warning formWarningEntity(WarningEntity warningEntity) {
        return Warning.builder()
                .id(warningEntity.getId())
                .warningProvider(warningEntity.getWarningProvider())
                .warnerUuid(warningEntity.getWarnerUuid())
                .warningType(warningEntity.getWarningType())
                .warningTitle(warningEntity.getWarningTitle())
                .warningContent(warningEntity.getWarningContent())
                .warningUrl(warningEntity.getWarningUrl())
                .warnerInt(warningEntity.getWarnerInt())
                .build();

    }

}
