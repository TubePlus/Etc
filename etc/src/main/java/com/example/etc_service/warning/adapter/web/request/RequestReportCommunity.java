package com.example.etc_service.warning.adapter.web.request;

import com.example.etc_service.warning.domain.WarningType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestReportCommunity {
//    실제로 들어오는 데이터의 형식
    @NotNull
    private Long warningProvider;
    @NotNull
    private WarningType warningType;
    private String warningTitle;
    private String warningContent;
    private String warningUrl;
    private Long warnerInt; // nullable
}
