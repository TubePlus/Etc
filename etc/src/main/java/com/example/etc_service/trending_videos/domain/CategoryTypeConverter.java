package com.example.etc_service.trending_videos.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class CategoryTypeConverter implements AttributeConverter<CategoryType, Long> {
    @Override
    public Long convertToDatabaseColumn(CategoryType attribute) {
        return attribute.getCode();
    }

    @Override
    public CategoryType convertToEntityAttribute(Long dbData) {
        return EnumSet.allOf(CategoryType.class).stream()
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 코드입니다."));
    }
}
