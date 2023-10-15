package com.example.etc_service.warning.adapter.web.request;

import com.example.etc_service.warning.domain.WarningType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestReportBoards {
    //    실제로 들어오는 데이터의 형식
    private Long warningProvider;
    private WarningType warningType;
    private String warningTitle;
    private String warningContent;
    private String warningUrl;
    private Long warnerInt; // nullable
}
