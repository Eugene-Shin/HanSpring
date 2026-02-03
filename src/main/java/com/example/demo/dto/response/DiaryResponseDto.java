package com.example.demo.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
public class DiaryResponseDto {
    private final Long diaryId;
    private final LocalDate createdAt;
    private final String text;
}
