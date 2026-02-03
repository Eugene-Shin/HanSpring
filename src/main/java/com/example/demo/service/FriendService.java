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
                .owner(userRepository.findUserById(friendDto.getOwnerId()))
                .friend(userRepository.findUserById(friendDto.getFriendId()))
                .build();

        friendRepository.save(friend);

        return true;
    }

    public Map<String, List<UserDto>> getFriends(Long owner_id) {
        List<UserDto> list = new ArrayList<>();
        Map<String, List<UserDto>> map = new HashMap<>();

        User owner = userRepository.findUserById(owner_id);
        if(owner == null) {
            throw new ResourceAccessException("존재하지 않는 사용자입니다");
        }

        List<Friend> friendships1 = friendRepository.findFriendsByOwner(owner).orElseThrow(() -> new ResourceAccessException("친구 관계가 없어용"));
        List<Friend> friendships2 = friendRepository.findFriendsByFriend(owner).orElseThrow(() -> new ResourceAccessException("친구 관계가 없어용"));
        for(Friend f : friendships1) {
            if(f.getOwner().getId().equals(owner_id)) {
                list.add(UserDto.builder()
                        .sex(f.getFriend().getSex())
                        .email(f.getFriend().getEmail())
                        .name(f.getFriend().getName())
                        .birthday(f.getFriend().getBirthday())
                        .phoneNumber(f.getFriend().getPhoneNumber())
                        .build()
                );
            }
        }
        for(Friend o : friendships2) {
            UserDto userDto = UserDto.builder()
                    .sex(o.getOwner().getSex())
                    .email(o.getOwner().getEmail())
                    .name(o.getOwner().getName())
                    .birthday(o.getOwner().getBirthday())
                    .phoneNumber(o.getOwner().getPhoneNumber())
                    .build();
            if(o.getFriend().getId().equals(owner_id)) {
                boolean alreadyInserted = false;
                for(UserDto dto : list) {
                    if(dto.getPhoneNumber().equals(userDto.getPhoneNumber())) {
                        alreadyInserted = true;
                        break;
                    }
                }
                if(!alreadyInserted) {
                    list.add(userDto);
                }
            }
        }

        map.put("friends", list);

        return map;
    }
}
