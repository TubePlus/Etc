package com.example.etc_service.trending_videos.dto.response;

import com.example.etc_service.trending_videos.domain.CategoryType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
