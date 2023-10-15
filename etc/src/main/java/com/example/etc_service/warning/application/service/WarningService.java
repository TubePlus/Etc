package com.example.etc_service.warning.application.service;

import com.example.etc_service.warning.application.ports.in.WarningUseCase;
import com.example.etc_service.warning.application.ports.out.dto.WarningDto;
import com.example.etc_service.warning.application.ports.out.port.WarningPort;
import com.example.etc_service.warning.domain.Warning;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WarningService implements WarningUseCase {
    private final WarningPort warningPort;
    @Override
    public WarningDto saveWarningCommunity(SaveWarningQuery saveWarningQuery) {
        Warning warning = warningPort.saveWarningCommunity(Warning.saveWarning(
                saveWarningQuery.getWarningProvider(),
                saveWarningQuery.getWarnerUuid(),
                saveWarningQuery.getWarningType(),
                saveWarningQuery.getWarningTitle(),
                saveWarningQuery.getWarningContent(),
                saveWarningQuery.getWarningUrl(),
                saveWarningQuery.getWarnerInt()));
        return WarningDto.formWarning(warning);
    }

    @Override
    public WarningDto saveWarningUsers(SaveWarningQuery saveWarningQuery) {
        Warning warning = warningPort.saveWarningUsers(Warning.saveWarning(
                saveWarningQuery.getWarningProvider(),
                saveWarningQuery.getWarnerUuid(),
                saveWarningQuery.getWarningType(),
                saveWarningQuery.getWarningTitle(),
                saveWarningQuery.getWarningContent(),
                saveWarningQuery.getWarningUrl(),
                saveWarningQuery.getWarnerInt()));
        return WarningDto.formWarning(warning);
    }

    @Override
    public WarningDto saveWarningBoards(SaveWarningQuery saveWarningQuery) {
        Warning warning = warningPort.saveWarningBoards(Warning.saveWarning(
                saveWarningQuery.getWarningProvider(),
                saveWarningQuery.getWarnerUuid(),
                saveWarningQuery.getWarningType(),
                saveWarningQuery.getWarningTitle(),
                saveWarningQuery.getWarningContent(),
                saveWarningQuery.getWarningUrl(),
                saveWarningQuery.getWarnerInt()));
        return WarningDto.formWarning(warning);
    }
//    비즈니스 모델을 수행하도록 하기 위함


}
