package com.example.etc_service.trending_videos.infrastructure;

import com.example.etc_service.trending_videos.domain.CategoryType;
import com.example.etc_service.trending_videos.domain.TrendingVideo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TrendingVideoRepository extends JpaRepository<TrendingVideo, Long> {

    List<TrendingVideo> findTop3ByVideoCategoryAndCreatedDateAfter(CategoryType categoryType, LocalDateTime today);
}
