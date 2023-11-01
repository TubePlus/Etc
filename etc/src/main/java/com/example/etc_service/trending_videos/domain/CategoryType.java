package com.example.etc_service.trending_videos.domain;

import com.example.etc_service.global.base.CodeValue;
import lombok.Getter;

@Getter
public enum CategoryType implements CodeValue {
    MOVIE(1L,"영화/애니메이션"),
    CAR(2L,"자동차"),
    MUSIC(10L,"음악"),
    ANIMAL(15L,"반려동물/동물"),
    SPORTS(17L,"스포츠"),
    TRAVEL(19L,"여행/이벤트"),
    GAME(20L,"게임"),
    BLOG(22L,"인물/블로그"),
    COMEDY(23L,"코미디"),
    ENTERTAINMENT(24L,"엔터테인먼트"),
    NEWS(25L,"뉴스/정치"),
    STYLE(26L,"노하우/스타일"),
    EDUCATION(27L,"교육"),
    SCIENCE(28L,"과학기술");

    private final Long code;
    private final String value;
    CategoryType(Long code, String value) {
        this.code = code;
        this.value = value;
    }
    @Override
    public Long getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }
}
