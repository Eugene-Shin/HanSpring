package com.example.demo.domain;

import com.example.demo.dto.request.UpdateDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@DynamicUpdate
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy="user")
    private final List<Diary> diaries = new ArrayList<>();

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @Column(name = "birthday", updatable = false)
    private LocalDate birthday;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Builder
    public User(String name, String sex, LocalDate birthday, String phoneNumber, String email) {
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    public void updateUserInfo(UpdateDto updateDto) {
        this.phoneNumber = updateDto.getPhoneNumber();
        this.email = updateDto.getEmail();
    }

    public void addDiary(Diary diary) {
        diaries.add(diary);
    }
}
