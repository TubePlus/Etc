package com.example.etc_service.config.kafka;

import com.example.etc_service.alarm.application.AlarmService;
import com.example.etc_service.alarm.application.SseService;
import com.example.etc_service.alarm.dto.AlarmDto;
import com.example.etc_service.config.kafka.dto.BoardCreateAlarmDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class KafkaConsumer {
    private final SseService sseService;
    private final AlarmService alarmService;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @KafkaListener(topics = "test", groupId = "etc-service")
    public void processMessage(String kafkaMessage){
        log.info("kafka message received =====> " + kafkaMessage);

        Map<Object, Object> map = new HashMap<>(); // kafka 역직렬화
        try{
            map = objectMapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
//            map = objectMapper.readValue(kafkaMessage, class 명);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // 저장할 때 Object Type이라서 String으로 변환해서 저장해야함
//        repository.findById((String)map.get(id))
    }

    @KafkaListener(topics = "boardCreateAlarm", groupId = "etc-service")
    public void consumeBoardCreateAlarm(String message) throws JsonProcessingException {
        log.info("kafka message received =====> " + message);
        BoardCreateAlarmDto boardCreateAlarmDto = objectMapper.readValue(message, BoardCreateAlarmDto.class);
        for (String uuid : boardCreateAlarmDto.getMembers()) {
            try {
                AlarmDto alarmDto = alarmService
                        .createBoardCreateAlarm(uuid, boardCreateAlarmDto.getAuthorUuid(), boardCreateAlarmDto.getBoard());
                sseService.sendBoardCreateAlarm(uuid, alarmDto);
            } catch (Exception e) {
                log.error("Error with UUID " + uuid + ", continuing to next. Error: ", e);
                // 연결되지 않는 유저에 대한 메시지 처리
            }
        }
    }
}
