package com.example.etc_service.warning.adapter.infrastructure.mysql.entity;

import com.example.etc_service.warning.domain.WarningType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "warning")
public class WarningEntity {
// 실제 저장할 코드 로직을 여기서 정의
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "warning_provider")
    private Long warningProvider;
    @Column(name = "warner_uuid")
    private String warnerUuid; // nullable
    @Column(nullable = false, name = "warning_type")
    private WarningType warningType;
    @Column(nullable = false, name = "warning_title")
    private String warningTitle;
    @Column(nullable = false, name = "warning_content")
    private String warningContent;
    @Column(nullable = false, name = "warning_url")
    private String warningUrl;
    @Column(name = "warner_int")
    private Long warnerInt; // nullable
//    in

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
//    out

}
