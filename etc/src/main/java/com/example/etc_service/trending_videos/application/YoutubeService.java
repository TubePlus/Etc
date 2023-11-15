package com.example.etc_service.trending_videos.application;

import com.example.etc_service.trending_videos.dto.response.GetTrendingVideoDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface YoutubeService {
    void getTrendingVideos() throws JsonProcessingException;
}
