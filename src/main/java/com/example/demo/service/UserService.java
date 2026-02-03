package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.request.UpdateDto;
import com.example.demo.dto.request.UserDto;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean createUser(UserDto userDto) {
        User newUser = User.builder()
                .name(userDto.getName())
                .sex(userDto.getSex())
                .email(userDto.getEmail())
                .birthday(userDto.getBirthday())
                .phoneNumber(userDto.getPhoneNumber())
                .build();

        userRepository.save(newUser);

        // merge 예제

        return true;
    }

    public Map<String, Long> getUserId(String phoneNumber) {
        User user = userRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(() -> new NullPointerException());
        Map<String, Long> result = new HashMap<>();
        result.put("userId", user.getId());
        return result;
    }

    public boolean updateUser(UpdateDto updateDto) {
        User user = userRepository.findUserById(updateDto.getUserId());
        user.updateUserInfo(updateDto);

        return true;
    }
}