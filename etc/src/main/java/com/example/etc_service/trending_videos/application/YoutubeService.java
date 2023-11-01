package com.example.etc_service.trending_videos.application;

import com.example.etc_service.trending_videos.application.response.GetTrendingVideoDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface YoutubeService {

    List<GetTrendingVideoDto> getTrendingVideos() throws JsonProcessingException;
}
