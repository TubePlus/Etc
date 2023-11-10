package com.example.etc_service.config.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    @KafkaListener(topics = "test", groupId = "etc-service")
    public void processMessage(String kafkaMessage){
        log.info("kafka message received =====> " + kafkaMessage);

        Map<Object, Object> map = new HashMap<>(); // kafka 역직렬화
        ObjectMapper mapper = new ObjectMapper();
        try{
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // 저장할 때 Object Type이라서 String으로 변환해서 저장해야함
//        repository.findById((String)map.get(id))
    }
}
