package com.mabcci.domain.chat.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
    @Query("select chr from ChatRoom chr join fetch chr.chatting where chr.id = :id")
    Optional<ChatRoom> findById(String id);
}
