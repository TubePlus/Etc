package com.example.etc_service.warning.adapter.web.controller;

import com.example.etc_service.global.base.ApiResponse;
import com.example.etc_service.warning.adapter.web.request.RequestReportBoards;
import com.example.etc_service.warning.adapter.web.request.RequestReportCommunity;
import com.example.etc_service.warning.adapter.web.request.RequestReportUsers;
import com.example.etc_service.warning.application.ports.in.WarningUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/etc")
public class WarningController {

    private final WarningUseCase warningUseCase;

    @GetMapping("/welcome")
//    gateway test용
    public String welcome() {
        return "Welcome to the Warning Service.";
    }

    @PostMapping("/reports/community")
    public ApiResponse<Object> reportCommunity(@RequestBody @Valid RequestReportCommunity reportCommunity) {
//        커뮤니티 신고 생성 로직
//        log.info("reportCommunity input data : "+String.valueOf(reportCommunity));
//        log.info("reportCommunity query : "+String.valueOf(WarningUseCase.SaveWarningQuery.toQeury(reportCommunity)));
//        useCase를 통해 port로 전달하는데까지
        warningUseCase.saveWarningCommunity(WarningUseCase.SaveWarningQuery.toQeury(reportCommunity));
        return ApiResponse.ofSuccess();
    }
    @PostMapping("/reports/uesrs")
    public ApiResponse<Object> reportUsers(@RequestBody @Valid RequestReportUsers reportUsers) {
//        유저 신고 생성 로직
        log.info(String.valueOf(reportUsers));
        warningUseCase.saveWarningUsers(WarningUseCase.SaveWarningQuery.toQeury(reportUsers));
        return ApiResponse.ofSuccess();
    }
    @PostMapping("/reports/boards")
    public ApiResponse<Object> reportBoards(@RequestBody @Valid RequestReportBoards requestReportBoards) {
//        보드 신고 생성 로직
        log.info(String.valueOf(requestReportBoards));
        warningUseCase.saveWarningBoards(WarningUseCase.SaveWarningQuery.toQeury(requestReportBoards));
        return ApiResponse.ofSuccess();
    }
}
