package com.example.demo.service;

import com.example.demo.domain.Diary;
import com.example.demo.domain.User;
import com.example.demo.dto.request.DiaryDto;
import com.example.demo.dto.response.DiaryResponseDto;
import com.example.demo.repository.DiaryRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    public boolean createDiary(DiaryDto diaryDto) {

        User user = userRepository.findUserById(diaryDto.getUserId());
        Diary diary = Diary.builder()
                .user(user)
                .createdAt(diaryDto.getCreatedAt())
                .text(diaryDto.getText())
                .build();

        diaryRepository.save(diary);
        user.addDiary(diary);

        return true;
    }

    public Map<String, List<DiaryResponseDto>> getDiaries(Long userId) {
        User user = userRepository.findUserById((userId));

        List<DiaryResponseDto> list = new ArrayList<>();
        Map<String, List<DiaryResponseDto>> map = new HashMap<>();

        for(Diary diary : user.getDiaries()) {
            list.add(
                    DiaryResponseDto.builder()
                            .diaryId(diary.getId())
                            .createdAt(diary.getCreatedAt())
                            .text(diary.getText())
                            .build()
            );
        }

        map.put("diaries", list);

        return map;
    }

    //순환 참조 발생하는 예시
//    public Map<String, List<Diary>> getDiary(Long userId) {
//        User user = userRepository.findUserById(userId);
//
//        Map<String, List<Diary>> map = new HashMap<>();
//        map.put("diaries", user.getDiaries());
//
//        return map;
//    }

    public DiaryResponseDto getDiary(Long diaryId) {
        Diary diary = diaryRepository.findDiaryById(diaryId).orElseThrow(() -> new NullPointerException());
        return DiaryResponseDto.builder()
                .createdAt(diary.getCreatedAt())
                .text(diary.getText())
                .build();
    }
}
