package com.example.etc_service.trending_videos.vo;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseTrendingVideo {
    private String videoTitle; // title
    private Long ranking; //
    private Long videoCategory; // categoryId
    private String videoCategoryName; // categoryName
    private String thumbnailUrl; // thumbnails.standard
    private String channelName; // channelTitle
    private String channelId; // id
    private String videoUrl; // player.embedHtml
}
