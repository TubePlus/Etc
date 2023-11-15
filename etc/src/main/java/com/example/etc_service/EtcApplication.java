package com.example.etc_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableKafka // kafka 활성화
@EnableScheduling // 스케줄링 활성화
@EnableJpaAuditing // base entity 자동 적용
@SpringBootApplication
@Cacheable
public class EtcApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtcApplication.class, args);
	}

}
