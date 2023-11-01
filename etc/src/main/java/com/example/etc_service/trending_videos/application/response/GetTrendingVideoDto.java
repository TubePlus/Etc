package com.example.etc_service.trending_videos.application.response;

import com.example.etc_service.trending_videos.domain.CategoryType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetTrendingVideoDto {
    private String videoTitle; // title
    private Long ranking; //
    private CategoryType videoCategory; // categoryId
    private String thumbnailUrl; // thumbnails.standard
    private String channelName; // channelTitle
    private String channelId; // id
    private String videoUrl; // player.embedHtml
}
