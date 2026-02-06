package com.example.demo.dto.request;

import com.example.demo.domain.Diary;
import com.example.demo.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

//@Getter
//@RequiredArgsConstructor
//public class DiaryDto {
//    private final Long userId;
//    private final LocalDate createdAt;
//    private final String text;
//}

public record DiaryDto (
        Long userId,
        LocalDate createdAt,
        String text
) {
    public Diary toEntity(User user) {

        return Diary.builder()
                .user(user)
                .createdAt(createdAt)
                .text(text)
                .build();
    }
}