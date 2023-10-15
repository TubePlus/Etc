package com.example.etc_service.warning.adapter.infrastructure.mysql.repository;

import com.example.etc_service.warning.adapter.infrastructure.mysql.entity.WarningEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarningRepository extends JpaRepository<WarningEntity,Long> {
}
