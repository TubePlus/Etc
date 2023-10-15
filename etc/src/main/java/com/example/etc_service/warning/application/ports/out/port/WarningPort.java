package com.example.etc_service.warning.application.ports.out.port;

import com.example.etc_service.warning.domain.Warning;

public interface WarningPort {
    Warning saveWarningCommunity(Warning warning);
    Warning saveWarningUsers(Warning warning);
    Warning saveWarningBoards(Warning warning);
}
