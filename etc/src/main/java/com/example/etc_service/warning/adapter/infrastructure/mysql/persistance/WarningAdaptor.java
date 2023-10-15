package com.example.etc_service.warning.adapter.infrastructure.mysql.persistance;

import com.example.etc_service.warning.adapter.infrastructure.mysql.entity.WarningEntity;
import com.example.etc_service.warning.adapter.infrastructure.mysql.repository.WarningRepository;
import com.example.etc_service.warning.application.ports.out.port.WarningPort;
import com.example.etc_service.warning.domain.Warning;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WarningAdaptor implements WarningPort {
    private final WarningRepository warningRepository;
    @Override
    public Warning saveWarningCommunity(Warning warning) {
        WarningEntity warningEntity = warningRepository.save(WarningEntity.saveWarning(
                warning.getWarningProvider(),
                warning.getWarnerUuid(),
                warning.getWarningType(),
                warning.getWarningTitle(),
                warning.getWarningContent(),
                warning.getWarningUrl(),
                warning.getWarnerInt()
        ));
        return Warning.formWarningEntity(warningEntity);
    }

    @Override
    public Warning saveWarningUsers(Warning warning) {
        WarningEntity warningEntity = warningRepository.save(WarningEntity.saveWarning(
                warning.getWarningProvider(),
                warning.getWarnerUuid(),
                warning.getWarningType(),
                warning.getWarningTitle(),
                warning.getWarningContent(),
                warning.getWarningUrl(),
                warning.getWarnerInt()
        ));
        return Warning.formWarningEntity(warningEntity);
    }

    @Override
    public Warning saveWarningBoards(Warning warning) {
        WarningEntity warningEntity = warningRepository.save(WarningEntity.saveWarning(
                warning.getWarningProvider(),
                warning.getWarnerUuid(),
                warning.getWarningType(),
                warning.getWarningTitle(),
                warning.getWarningContent(),
                warning.getWarningUrl(),
                warning.getWarnerInt()
        ));
        return Warning.formWarningEntity(warningEntity);
    }
}
