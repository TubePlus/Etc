package com.example.etc_service.trending_videos.application;


import com.example.etc_service.trending_videos.dto.response.GetTrendingVideoDto;
import com.example.etc_service.trending_videos.domain.CategoryTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
@ToString
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class YoutubeServiceImpl implements YoutubeService{
    private final WebClient webClient;
    private final Environment env;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
//    @CachePut(value = "trendingVideos", key = "'trendingVideos:' + T(java.time.LocalDate).now().toString()",
//            cacheManager = "redisCacheManager")
    public void getTrendingVideos() throws JsonProcessingException {

        ListOperations<String, Object> redisListOperations = redisTemplate.opsForList();

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
//        List<GetTrendingVideoDto> trendingVideos = new ArrayList<>();


        // 밑에 코드를 stream으로 바꾸기
        int ranking = 0;
        for (JsonNode item : itemsNode) {
            GetTrendingVideoDto videoDto = new GetTrendingVideoDto();
            ranking++;
            // videoUrl에서 YouTube 동영상 URL 추출
            String videoHtml = item.get("player").get("embedHtml").asText();
            Matcher videoMatcher = pattern.matcher(videoHtml);
            if (videoMatcher.find()) {
                videoDto.setVideoUrl(videoMatcher.group(1)); // 첫 번째 캡쳐 그룹에 일치하는 부분 반환
            }
            videoDto.setVideoTitle(item.get("snippet").get("title").asText());
            videoDto.setRanking((long) ranking);
            videoDto.setVideoCategory(new CategoryTypeConverter().convertToEntityAttribute(item.get("snippet").get("categoryId").asLong()));
            videoDto.setThumbnailUrl(item.get("snippet").get("thumbnails").get("standard").get("url").asText());
            videoDto.setChannelName(item.get("snippet").get("channelTitle").asText());
            videoDto.setChannelId(item.get("id").asText());

            // 레디스 캐시에 저장
            redisListOperations.rightPush("trendingVideos:" + LocalDate.now().toString(), videoDto);
        }
    }
}
