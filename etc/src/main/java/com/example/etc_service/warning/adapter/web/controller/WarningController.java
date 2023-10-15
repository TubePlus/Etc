package com.example.etc_service.warning.adapter.web.controller;

import com.example.etc_service.warning.adapter.web.request.RequestReportBoards;
import com.example.etc_service.warning.adapter.web.request.RequestReportCommunity;
import com.example.etc_service.warning.adapter.web.request.RequestReportUsers;
import com.example.etc_service.warning.application.ports.in.WarningUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> reportCommunity(@RequestBody RequestReportCommunity reportCommunity) {
//        커뮤니티 신고 생성 로직
        log.info(String.valueOf(reportCommunity));
//        useCase를 통해 port로 전달하는데까지
        warningUseCase.saveWarningCommunity(WarningUseCase.SaveWarningQuery.toQeury(reportCommunity));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/reports/uesrs")
    public ResponseEntity<Object> reportUsers(@RequestBody RequestReportUsers reportUsers) {
//        유저 신고 생성 로직
        log.info(String.valueOf(reportUsers));
        warningUseCase.saveWarningUsers(WarningUseCase.SaveWarningQuery.toQeury(reportUsers));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/reports/boards")
    public ResponseEntity<Object> reportBoards(@RequestBody RequestReportBoards requestReportBoards) {
//        보드 신고 생성 로직
        log.info(String.valueOf(requestReportBoards));
        warningUseCase.saveWarningBoards(WarningUseCase.SaveWarningQuery.toQeury(requestReportBoards));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
