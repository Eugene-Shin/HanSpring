package com.example.demo.service;

import com.example.demo.domain.Friend;
import com.example.demo.domain.User;
import com.example.demo.dto.request.FriendDto;
import com.example.demo.dto.request.UserDto;
import com.example.demo.repository.FriendRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendService {
    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

    public boolean addFriend(FriendDto friendDto) {
        Friend friend = Friend.builder()
                .createdAt(friendDto.getCreatedAt())
                .user2(userRepository.findUserById(friendDto.getOwnerId()))
                .user1(userRepository.findUserById(friendDto.getFriendId()))
                .build();

        friendRepository.save(friend);

        return true;
    }

    public Map<String, List<UserDto>> getFriends(Long id) {
        List<UserDto> list = new ArrayList<>();
        Map<String, List<UserDto>> map = new HashMap<>();

        User owner = userRepository.findUserById(id);
        if(owner == null) {
            throw new ResourceAccessException("존재하지 않는 사용자입니다");
        }
        List<UserDto> userList = userRepository.findFriendsById(id).stream()
                .map(user -> UserDto.builder()
                        .sex(user.getSex())
                        .name(user.getName())
                        .email(user.getEmail())
                        .phoneNumber(user.getPhoneNumber())
                        .birthday(user.getBirthday())
                        .build()
                ).toList();


        map.put("friends", list);

        return map;
    }
}
