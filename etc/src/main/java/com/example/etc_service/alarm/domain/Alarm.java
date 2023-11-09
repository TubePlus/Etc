package com.example.etc_service.alarm.domain;

import com.example.etc_service.trending_videos.domain.CategoryType;
import com.example.etc_service.trending_videos.domain.CategoryTypeConverter;
import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor // 프록시때문에 만드는 것
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "alarm")
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "uuid", nullable = false)
    private String uuid;
    @Column(name = "alarm_title")
    private String alarmTitle;
    @Column(name = "alarm_content")
    private String alarmContent; // url
    @Column(name = "alarm_check", columnDefinition = "boolean default false")
    private Boolean alarmCheck;
    @Column(name = "author_uuid", nullable = false)
    private String authorUuid;
    public void setRead(Boolean alarmCheck) {
        this.alarmCheck = alarmCheck;
    }
}
