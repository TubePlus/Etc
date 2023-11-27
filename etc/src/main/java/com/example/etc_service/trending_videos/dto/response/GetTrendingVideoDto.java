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
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "@class"
)
public class GetTrendingVideoDto {

    @JsonProperty("title")
    private String videoTitle; // title
    @JsonProperty("ranking")
    private Long ranking; //
    @JsonProperty("video_category")
    private CategoryType videoCategory; // categoryId
    @JsonProperty("thumbnail_url")
    private String thumbnailUrl; // thumbnails.standard
    @JsonProperty("channel_name")
    private String channelName; // channelTitle
    @JsonProperty("channel_id")
    private String channelId; // id
    @JsonProperty("video_url")
    private String videoUrl; // player.embedHtml
}
