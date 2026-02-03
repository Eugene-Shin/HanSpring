package com.example.demo.controller;

import com.example.demo.dto.request.FriendDto;
import com.example.demo.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {
    private final FriendService friendService;

    @PostMapping("")
    public ResponseEntity<Boolean> addFriends(@RequestBody FriendDto friendDto) {
        return ResponseEntity.ok(friendService.addFriend(friendDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getFriends(@PathVariable Long userId) {
        return ResponseEntity.ok(friendService.getFriends(userId));
    }
}
