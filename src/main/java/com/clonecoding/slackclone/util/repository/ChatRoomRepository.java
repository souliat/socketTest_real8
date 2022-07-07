package com.clonecoding.slackclone.util.repository;

import com.clonecoding.slackclone.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findAllByOrderByCreatedAtDesc();

    Optional<ChatRoom> findById(Long id);
}