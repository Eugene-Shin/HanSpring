package com.example.demo.repository;

import com.example.demo.domain.Diary;
import com.example.demo.dto.request.DiaryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    public Optional<Diary> findDiaryById(Long diaryId);
}
