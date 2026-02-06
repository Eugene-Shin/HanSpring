package com.example.demo.dto.request;

import com.example.demo.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

//@Builder
//@Getter
//@RequiredArgsConstructor
//public class UserDto {
//    private final String name;
//
//    private final String sex;
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
//    private final LocalDate birthday;
//
//    private final String phoneNumber;
//
//    private final String email;
//}

public record UserDto (
        @NotBlank
        String name,
        @NotBlank
        String sex,
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        LocalDate birthday,
        @NotBlank
        String phoneNumber,
        @NotBlank
        String email
) {
    public User toEntity() {
        return User.builder()
                .name(name)
                .sex(sex)
                .birthday(birthday)
                .phoneNumber(phoneNumber)
                .email(email)
                .build();
    }
}