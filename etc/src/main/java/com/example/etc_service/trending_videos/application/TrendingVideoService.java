package com.example.etc_service.trending_videos.application;

import java.util.List;

public interface TrendingVideoService {

//    void saveTrendingVideosz(List<GetTrendingVideoDto> youtubeVideos);
//    List<ResponseTrendingVideo> getByCategoryId(Long categoryId);
    List<Object> getTrendingVideos();
}
