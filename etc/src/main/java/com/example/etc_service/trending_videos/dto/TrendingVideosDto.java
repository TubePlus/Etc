package com.example.etc_service.trending_videos.dto;

import com.example.etc_service.trending_videos.domain.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TrendingVideosDto {
    private String videoTitle; // title
    private Long ranking; //
    private CategoryType videoCategory; // categoryId
    private String thumbnailUrl; // thumbnails.standard
    private String channelName; // channelTitle
    private String channelId; // id
    private String videoUrl; // player.embedHtml
}
