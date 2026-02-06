package com.example.demo.dto.request;

import com.example.demo.domain.Friend;
import com.example.demo.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

//@RequiredArgsConstructor
//@Getter
//public class FriendDto {
//    private final LocalDate createdAt;
//    private final Long ownerId;
//    private final Long friendId;
//}

public record FriendDto (
        LocalDate createdAt,
        Long user1Id,
        Long user2Id
) {
    public Friend toEntity(User user1, User user2) {
        return Friend.builder()
                .user1(user1)
                .user2(user2)
                .createdAt(createdAt)
                .build();
    }
}
