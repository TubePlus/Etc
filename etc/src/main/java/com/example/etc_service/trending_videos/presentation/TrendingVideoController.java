package com.example.etc_service.trending_videos.presentation;

import com.example.etc_service.global.base.ApiResponse;
import com.example.etc_service.trending_videos.application.TrendingVideoService;
import com.example.etc_service.trending_videos.vo.ResponseTrendingVideo;
import com.example.etc_service.trending_videos.vo.TrendingVideosDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/etc")
public class TrendingVideoController {
    private final TrendingVideoService trendingVideoService;
    @GetMapping("/trendingVideos")
    public ApiResponse<?> getTrendingVideos(
            @RequestParam("categoryId") Long categoryId // todo: category 방법 명시하고 변경
    ) {
        List<ResponseTrendingVideo> trendVideos = trendingVideoService.getByCategoryId(categoryId);
        // 3개씩 가져오기
        return ApiResponse.ofSuccess(trendVideos);
    }
}
