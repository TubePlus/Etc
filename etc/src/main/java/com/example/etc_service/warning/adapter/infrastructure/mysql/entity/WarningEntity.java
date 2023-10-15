package com.example.etc_service.warning.adapter.infrastructure.mysql.entity;

import com.example.etc_service.warning.domain.WarningType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "warning")
public class WarningEntity {
// 실제 저장할 코드 로직을 여기서 정의
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long warningProvider;
    private String warnerUuid; // nullable
    private WarningType warningType;
    private String warningTitle;
    private String warningContent;
    private String warningUrl;
    private Long warnerInt; // nullable

    public static WarningEntity saveWarning(
            Long warningProvider, String warnerUuid, WarningType warningType,
            String  warningTitle, String warningContent, String warningUrl, Long warnerInt
    ){
        return WarningEntity.builder()
                .warningProvider(warningProvider)
                .warnerUuid(warnerUuid)
                .warningType(warningType)
                .warningTitle(warningTitle)
                .warningContent(warningContent)
                .warningUrl(warningUrl)
                .warnerInt(warnerInt)
                .build();
    }
}
