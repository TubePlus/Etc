package com.example.etc_service.trending_videos.presentation;

import com.example.etc_service.global.base.ApiResponse;
import com.example.etc_service.trending_videos.application.TrendingVideoService;
import com.example.etc_service.trending_videos.application.YoutubeService;
import com.example.etc_service.trending_videos.application.YoutubeServiceImpl;
import com.example.etc_service.trending_videos.dto.response.GetTrendingVideoDto;
import com.example.etc_service.trending_videos.vo.ResponseTrendingVideo;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/etc")
public class TrendingVideoController {
    private final TrendingVideoService trendingVideoService;
//    @Operation(summary = "인기 영상 카테고리 별 찾기 3개", description = "인기영상을 그날의 카테고리별로 3개 이하로 들고온다")
//    @GetMapping("/trendingVideos")
//    public ApiResponse<?> getTrendingVideos(
//            @RequestParam("categoryId") Long categoryId // todo: category 방법 명시하고 변경
//    ) {
//        List<ResponseTrendingVideo> trendVideos = trendingVideoService.getByCategoryId(categoryId);
//        // 3개씩 가져오기
//        return ApiResponse.ofSuccess(trendVideos);
//    }

    // 레디스 캐시 메모리에서 인급동 가져오기
    @Operation(summary = "인급동 동영상 가져오기")
    @GetMapping("/trendingVideos")
    public ApiResponse<Object> getTrendingVideos() {

        List<Object> trendingVideos = trendingVideoService.getTrendingVideos();

        return ApiResponse.ofSuccess(trendingVideos);
    }

    private final YoutubeService youtubeService;
    @PostMapping("/test")
    public void test() throws JsonProcessingException {

        youtubeService.getTrendingVideos();
    }
}
