package com.example.demo.controller;

import com.example.demo.dto.request.UpdateDto;
import com.example.demo.dto.request.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<Boolean> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<?> getUserId(@PathVariable String phoneNumber) {
        return ResponseEntity.ok(userService.getUserId(phoneNumber));
    }
    @PutMapping("")
    public ResponseEntity<Boolean> updateUser(@RequestBody UpdateDto updateDto) {
        return ResponseEntity.ok(userService.updateUser(updateDto));
    }
}
