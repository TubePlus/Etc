package com.example.etc_service.trending_videos.scheduler;

import com.example.etc_service.trending_videos.application.TrendingVideoService;
import com.example.etc_service.trending_videos.application.YoutubeService;
import com.example.etc_service.trending_videos.application.response.GetTrendingVideoDto;
import com.example.etc_service.trending_videos.domain.TrendingVideo;
import com.example.etc_service.trending_videos.infrastructure.TrendingVideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TrendingSchedulerTasks {
    private final YoutubeService youtubeService;
    private final TrendingVideoService trendingVideoService;
    @Scheduled(cron = "0 0 0 * * ?")  // 초 분 시 일 월 요일(일 1, 월 2--)
    public void executeTrendingVideos() {
        System.out.println("executeTrendingVideos");
        try {
            List<GetTrendingVideoDto> youtubeVideos = youtubeService.getTrendingVideos();
            trendingVideoService.saveTrendingVideosz(youtubeVideos);
            log.info(youtubeVideos.toString());
        } catch (Exception e) {
            log.error("Error executing trending videos task", e);
        }
    }

}