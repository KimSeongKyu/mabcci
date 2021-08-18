package com.mabcci.domain.chat.domain;

import com.mabcci.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ChattingRepository extends JpaRepository<Chatting, Long> {
    @Query("select ch from Chatting ch join fetch ch.chatRoom join fetch ch.member where ch.member = :member")
    Set<Chatting> findByMember(Member member);
}
