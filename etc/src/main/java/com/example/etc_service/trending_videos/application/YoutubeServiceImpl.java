package com.example.etc_service.trending_videos.application;


import com.example.etc_service.trending_videos.application.response.GetTrendingVideoDto;
import com.example.etc_service.trending_videos.domain.CategoryTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
@ToString
@RequiredArgsConstructor
public class YoutubeServiceImpl implements YoutubeService{
    private final WebClient webClient;
    private final Environment env;

    @Override
    public List<GetTrendingVideoDto> getTrendingVideos() throws JsonProcessingException {
        Pattern pattern = Pattern.compile("//(www\\.youtube\\.com\\/embed\\/[a-zA-Z0-9_-]+)");
        String partValues =
                "contentDetails,id,liveStreamingDetails,player,snippet,statistics,status,topicDetails";
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/videos")
                        .queryParam("part", partValues)
                        .queryParam("chart", "mostPopular")
                        .queryParam("maxResults", "50")
                        .queryParam("regionCode", "kr")
                        .queryParam("key",env.getProperty("YOUTUBE.API_KEY"))
                        .build()
                )
                .retrieve()
                .bodyToMono(String.class)
                .block();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode itemsNode = objectMapper.readTree(response).get("items");
        List<GetTrendingVideoDto> trendingVideos = new ArrayList<>();

        for (int i = 0; i < itemsNode.size(); i++) {
            JsonNode videoNode = itemsNode.get(i);
            GetTrendingVideoDto videoDto = new GetTrendingVideoDto();
            // videoUrl에서 YouTube 동영상 URL 추출
            String videoHtml = videoNode.get("player").get("embedHtml").asText();
            Matcher videoMatcher = pattern.matcher(videoHtml);
            if (videoMatcher.find()) {
                videoDto.setVideoUrl(videoMatcher.group(1)); // 첫 번째 캡쳐 그룹에 일치하는 부분 반환
            }

            videoDto.setVideoTitle(videoNode.get("snippet").get("title").asText());
            videoDto.setRanking((long) i + 1);
            videoDto.setVideoCategory(new CategoryTypeConverter().convertToEntityAttribute(videoNode.get("snippet").get("categoryId").asLong()));
            videoDto.setThumbnailUrl(videoNode.get("snippet").get("thumbnails").get("standard").get("url").asText());
            videoDto.setChannelName(videoNode.get("snippet").get("channelTitle").asText());
            videoDto.setChannelId(videoNode.get("id").asText());
            trendingVideos.add(videoDto);
        }

        return trendingVideos;
    }
}
