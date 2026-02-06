package com.example.demo.dto.response;

import com.example.demo.domain.Diary;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

//@Builder
//@Getter
//public class DiaryResponseDto {
//    private final Long diaryId;
//    private final LocalDate createdAt;
//    private final String text;
//}

public record DiaryResponseDto(
        @NotNull
        Long diaryId,
        @NotNull
        LocalDate createdAt,
        @NotBlank
        String text
) {
    public static DiaryResponseDto fromEntity(Diary diary) {
        return new DiaryResponseDto(
                diary.getId(),
                diary.getCreatedAt(),
                diary.getText()
        );
    }
}

