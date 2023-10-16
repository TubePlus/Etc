package com.example.etc_service.warning.application.ports.in;

import com.example.etc_service.warning.adapter.web.request.RequestReportBoards;
import com.example.etc_service.warning.adapter.web.request.RequestReportCommunity;
import com.example.etc_service.warning.adapter.web.request.RequestReportUsers;
import com.example.etc_service.warning.application.ports.out.dto.WarningDto;
import com.example.etc_service.warning.domain.WarningType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public interface WarningUseCase {
//    1. request를 dto로 변환시켜준다.
    WarningDto saveWarningCommunity(SaveWarningQuery saveWarningQuery);
    WarningDto saveWarningUsers(SaveWarningQuery saveWarningQuery);
    WarningDto saveWarningBoards(SaveWarningQuery saveWarningQuery);
    @Getter
    @ToString
    @Builder
    class SaveWarningQuery {
        private Long warningProvider;
        private String warnerUuid; // nullable
        private WarningType warningType;
        private String warningTitle;
        private String warningContent;
        private String warningUrl;
        private Long warnerInt; // nullable

        public static SaveWarningQuery toQeury(RequestReportCommunity requestReportCommunity) {
            return SaveWarningQuery.builder()
                    .warningProvider(requestReportCommunity.getWarningProvider())
                    .warnerUuid(null)
                    .warningType(requestReportCommunity.getWarningType())
                    .warningTitle(requestReportCommunity.getWarningTitle())
                    .warningContent(requestReportCommunity.getWarningContent())
                    .warningUrl(requestReportCommunity.getWarningUrl())
                    .warnerInt(requestReportCommunity.getWarnerInt())
                    .build();
        }
        public static SaveWarningQuery toQeury(RequestReportBoards requestReportBoards) {
            return SaveWarningQuery.builder()
                    .warningProvider(requestReportBoards.getWarningProvider())
                    .warnerUuid(null)
                    .warningType(requestReportBoards.getWarningType())
                    .warningTitle(requestReportBoards.getWarningTitle())
                    .warningContent(requestReportBoards.getWarningContent())
                    .warningUrl(requestReportBoards.getWarningUrl())
                    .warnerInt(requestReportBoards.getWarnerInt())
                    .build();
        }
        public static SaveWarningQuery toQeury(RequestReportUsers requestReportUsers) {
            return SaveWarningQuery.builder()
                    .warningProvider(requestReportUsers.getWarningProvider())
                    .warnerUuid(requestReportUsers.getWarnerUuid())
                    .warningType(requestReportUsers.getWarningType())
                    .warningTitle(requestReportUsers.getWarningTitle())
                    .warningContent(requestReportUsers.getWarningContent())
                    .warningUrl(requestReportUsers.getWarningUrl())
                    .warnerInt(null)
                    .build();
        }
    }
}
