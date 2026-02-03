package com.example.demo.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class DiaryDto {
    private final Long userId;
    private final LocalDate createdAt;
    private final String text;
}
