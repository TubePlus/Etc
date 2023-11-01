package com.example.etc_service.trending_videos.domain;

import com.example.etc_service.global.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "trending_videos")
public class TrendingVideo extends BaseEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "video_title")
        private String videoTitle;
        @Column(name = "ranking")
        private Long ranking;
        @Column(name = "video_category")
        @Convert(converter = CategoryTypeConverter.class)
        private CategoryType videoCategory;
        @Column(name = "thumbnail_url")
        private String thumbnailUrl;
        @Column(name = "channel_name")
        private String channelName;
        @Column(name = "channel_id")
        private String channelId;
        @Column(name = "video_url")
        private String videoUrl;
}
