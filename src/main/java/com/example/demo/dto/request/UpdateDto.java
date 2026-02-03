package com.example.demo.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateDto {
    private final Long userId;
    private final String phoneNumber;
    private final String email;
}
