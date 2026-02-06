package com.example.demo.dto.response;

import com.example.demo.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record UserResponseDto (
        String name,
        String sex,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        LocalDate birthday,
        String phoneNumber,
        String email
) {
    public static UserResponseDto fromEntity(User user) {
        return new UserResponseDto(
                user.getName(),
                user.getSex(),
                user.getBirthday(),
                user.getPhoneNumber(),
                user.getEmail()
        );
    }
}
