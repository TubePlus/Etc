package com.example.etc_service.trending_videos.application;


import com.example.etc_service.trending_videos.dto.response.GetTrendingVideoDto;
import com.example.etc_service.trending_videos.domain.CategoryType;
import com.example.etc_service.trending_videos.domain.CategoryTypeConverter;
import com.example.etc_service.trending_videos.domain.TrendingVideo;
import com.example.etc_service.trending_videos.infrastructure.TrendingVideoRepository;
import com.example.etc_service.trending_videos.vo.ResponseTrendingVideo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrendingVideoServiceImpl implements TrendingVideoService {
    private final TrendingVideoRepository trendingVideoRepository;
    private final ModelMapper modelMapper;

    @Override
    public void saveTrendingVideosz(List<GetTrendingVideoDto> youtubeVideos) {
        List<TrendingVideo> trendingVideos = youtubeVideos.stream()
                .map(this::convertToEntity)
                .toList();
        trendingVideoRepository.saveAll(trendingVideos);
    }

    @Override
    public List<ResponseTrendingVideo> getByCategoryId(Long categoryId) {
        LocalDateTime today = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
        System.out.println("today = " + today);
        CategoryType categoryType = new CategoryTypeConverter().convertToEntityAttribute(categoryId);
        // q query를 이용해 createdDate가 최신이면서 ranking이 높은 순으로 3개 들고오기
        List<TrendingVideo> videos = trendingVideoRepository.findTop3ByVideoCategoryAndCreatedDateAfter(categoryType, today);
        return videos.stream()
                .map(this::convertToTrendingVideosDto)
                .collect(Collectors.toList());
    }

    public TrendingVideo convertToEntity(GetTrendingVideoDto dto) {
        return TrendingVideo.builder()
                .videoTitle(dto.getVideoTitle())
                .ranking(dto.getRanking())
                .videoCategory(dto.getVideoCategory())
                .thumbnailUrl(dto.getThumbnailUrl())
                .channelName(dto.getChannelName())
                .channelId(dto.getChannelId())
                .videoUrl(dto.getVideoUrl())
                .build();
    }


    private ResponseTrendingVideo convertToTrendingVideosDto(TrendingVideo video) {
//        숫자로 된 db를 str로 만들고 그것을 enumtype의 value로 바꿔서 ReponseTrendingVideo에 대입하기

        return new ResponseTrendingVideo(
                video.getVideoTitle(),
                video.getRanking(),
                video.getVideoCategory().getCode(),
                video.getVideoCategory().getValue(),
                video.getVideoUrl(),
                video.getChannelName(),
                video.getChannelId(),
                video.getVideoUrl()
        );
    }
}
