package com.example.demo.controller;

import com.example.demo.dto.request.DiaryDto;
import com.example.demo.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @PostMapping("")
    public ResponseEntity<Boolean> createDiary(@RequestBody DiaryDto diaryDto) {
        return ResponseEntity.ok(diaryService.createDiary(diaryDto));
    }

    @GetMapping("/{userId}/diaries")
    public ResponseEntity<?> getDiaries(@PathVariable Long userId) {
        return ResponseEntity.ok(diaryService.getDiaries(userId));
    }

    @GetMapping("/{diaryId}")
    public ResponseEntity<?> getDiary(@PathVariable Long diaryId) {
        return ResponseEntity.ok(diaryService.getDiary(diaryId));
    }
}
