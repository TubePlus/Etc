package com.example.etc_service.alarm.infrastructure;

import com.example.etc_service.alarm.domain.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long>{
    List<Integer> findIdByUuidAndAlarmCheck(String uuid, boolean alarmCheck);
}
