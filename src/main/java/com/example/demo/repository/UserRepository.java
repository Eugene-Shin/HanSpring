package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findUserByPhoneNumber(String phoneNumber);
    public User findUserById(Long userId);

        @Query(
                value = """
            SELECT DISTINCT
                u.email,
                u.name,
                u.sex,
                u.birthday,
                u.phone_number,
                u.id
            FROM user u
            JOIN (
                SELECT f.user2_id AS friend_id
                FROM friend f
                WHERE f.user1_id = :id

                UNION

                SELECT f.user1_id AS friend_id
                FROM friend f
                WHERE f.user2_id = :id
            ) t
            ON u.id = t.friend_id
        """,
                nativeQuery = true
        )
        List<User> findFriendsById(@Param("id") Long id);

}