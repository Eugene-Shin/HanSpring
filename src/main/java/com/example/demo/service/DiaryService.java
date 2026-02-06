package com.example.demo.service;

import com.example.demo.domain.Diary;
import com.example.demo.domain.User;
import com.example.demo.dto.request.DiaryDto;
import com.example.demo.dto.response.DiaryResponseDto;
import com.example.demo.dto.response.PageDto;
import com.example.demo.repository.DiaryRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        User user = userRepository.findUserById(diaryDto.userId());
        Diary diary = diaryDto.toEntity(user);

        diaryRepository.save(diary);
        user.addDiary(diary);

        return true;
    }

//    public Map<String, List<DiaryResponseDto>> getDiaries(Long userId) {
//        User user = userRepository.findUserById((userId));
//
//        List<DiaryResponseDto> list = user.getDiaries().stream()
//                .map(DiaryResponseDto::fromEntity)
//                .toList();
//
//        Map<String, List<DiaryResponseDto>> map = new HashMap<>();
//        map.put("diaries", list);
//
//        return map;
//    }

    public PageDto<DiaryResponseDto> getDiaries(Long userId, Integer pageNum, Integer pageSize) {
        User user = userRepository.findUserById((userId));
        Pageable pageable = PageRequest.of(
                pageNum,
                pageSize,
                Sort.by(Sort.Direction.DESC, "createdAt")   //해당 도메인에 포함된 필드(DB 컬럼명 아니라 변수명)
        );

        Page<Diary> diaries = diaryRepository.findByUser(user, pageable);

        List<DiaryResponseDto> list = diaries.getContent().stream()
                .map(DiaryResponseDto::fromEntity)
                .toList();

        return PageDto.from(diaries, list);
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
//        return DiaryResponseDto.builder()
//                .createdAt(diary.getCreatedAt())
//                .text(diary.getText())
//                .build();
        return DiaryResponseDto.fromEntity(diary);
    }
}
