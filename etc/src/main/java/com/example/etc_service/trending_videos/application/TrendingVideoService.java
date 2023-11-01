package com.example.etc_service.trending_videos.application;

import com.example.etc_service.trending_videos.application.response.GetTrendingVideoDto;
import com.example.etc_service.trending_videos.vo.ResponseTrendingVideo;
import com.example.etc_service.trending_videos.vo.TrendingVideosDto;

import java.util.List;

public interface TrendingVideoService {

    void saveTrendingVideosz(List<GetTrendingVideoDto> youtubeVideos);
    List<ResponseTrendingVideo> getByCategoryId(Long categoryId);
}
