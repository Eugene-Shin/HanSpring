package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "friends")
@NoArgsConstructor
@Getter
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private User friend;

    @Builder
    public Friend(LocalDate createdAt, User friend, User owner) {
        this.createdAt = createdAt;
        this.friend = friend;
        this.owner = owner;
    }
}
