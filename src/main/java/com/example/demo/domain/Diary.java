package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "diary")
@NoArgsConstructor
@Getter
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(name="created_at")
    private LocalDate createdAt;

    @Lob
    @Column(name="text")
    private String text;

    @Builder
    public Diary(User user, LocalDate createdAt, String text) {
        this.user = user;
        this.createdAt = createdAt;
        this.text = text;
    }
}
