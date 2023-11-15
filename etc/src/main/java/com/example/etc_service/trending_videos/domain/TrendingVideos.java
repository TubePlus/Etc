package com.example.etc_service.trending_videos.domain;

import com.example.etc_service.trending_videos.dto.response.GetTrendingVideoDto;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Getter
@Builder
@RedisHash("trending_videos")
public class TrendingVideos {

    @Id
    private String id;
    private List<GetTrendingVideoDto> trendingVideos;
}