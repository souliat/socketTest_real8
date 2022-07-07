package com.clonecoding.slackclone.util.repository;

import com.clonecoding.slackclone.model.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    // 이것도 사실 String roomId를 Long roomId로 바꿔줘야 할듯.
    Page<ChatMessage> findByRoomId(String roomId, Pageable pageable);

    List<ChatMessage> findByRoomId(Long roomId);
}
