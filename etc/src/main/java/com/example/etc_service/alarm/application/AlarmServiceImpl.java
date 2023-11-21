package com.example.etc_service.alarm.application;

import com.example.etc_service.alarm.domain.Alarm;
import com.example.etc_service.alarm.dto.AlarmDto;
import com.example.etc_service.alarm.infrastructure.AlarmRepository;
import com.example.etc_service.config.kafka.dto.BoardDto;
import com.example.etc_service.global.error.ErrorCode;
import com.example.etc_service.global.error.handler.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {
    private AlarmRepository alarmRepository;
    @Override
    public void readAlarm(Long id) {
        Alarm alarm = alarmRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.NO_ENTITY_ALARM));
        alarm.setRead(true); // 읽음 처리
        alarmRepository.save(alarm); // 명시적 저장
    }
    @Override
    public List<Integer> getAlarmList(String uuid) {
        // uuid를 통해 data를 찾을때 읽지 않은것 만 가져오기
        return alarmRepository.findIdByUuidAndAlarmCheck(uuid, false);
    }
    @Override
    public void readAllAlarm(List<Long> ids) {
        List<Alarm> alarms = alarmRepository.findAllById(ids);
        // 가져온 Alarm 객체들을 읽음처리하기
        for (Alarm alarm : alarms) {
            alarm.setRead(true);
        }
        // 변경된 Alarm 객체들을 한 번의 쿼리로 저장하기
        alarmRepository.saveAll(alarms);
    }

    @Override
    public AlarmDto createBoardCreateAlarm(String uuid, String authorUuid, BoardDto baordDto) {
        // alarm 객체 생성
        Alarm alarm = Alarm.builder()
                .uuid(uuid) // 받는 사람
                .alarmTitle(baordDto.getBoardName())
                .alarmContent(baordDto.getBoardDescription())
                .alarmCheck(false)
                .authorUuid(authorUuid)  // 보낸 사람
                .build();
        alarmRepository.save(alarm);
        return AlarmDto.builder()
                .uuid(alarm.getUuid())
                .alarmTitle(alarm.getAlarmTitle())
                .alarmContent(alarm.getAlarmContent())
                .alarmCheck(alarm.getAlarmCheck())
                .authorUuid(alarm.getAuthorUuid())
                .build();

    }
}
