package com.example.etc_service.alarm.adapter.infrastructure.mysql.entity;

import com.example.etc_service.warning.domain.WarningType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class AlarmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "uuid")
    private String uuid;
    @Column(name = "alarm_title")
    private String alarmTitle;
    @Column(name = "alarm_content")
    private String alarmContent;
    @Column(nullable = false, name = "ckeck")
    private String check;
    @Column(name = "author_uuid")
    private String authorUuid;
}
