package com.example.demo.dto.request;

import com.example.demo.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
public class FriendDto {
    private final LocalDate createdAt;
    private final Long ownerId;
    private final Long friendId;
}
