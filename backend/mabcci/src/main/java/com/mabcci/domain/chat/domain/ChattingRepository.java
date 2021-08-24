package com.mabcci.domain.chat.domain;

import com.mabcci.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface ChattingRepository extends JpaRepository<Chatting, Long> {
    @Query("select ch from Chatting ch join fetch ch.chatRoom join fetch ch.proposal join fetch ch.mabcci where ch.proposal = :proposal")
    Set<Chatting> findByProposal(Member proposal);

    @Query("select ch from Chatting ch join fetch ch.chatRoom join fetch ch.proposal join fetch ch.mabcci where ch.proposal = :proposal and ch.mabcci = :mabcci")
    Optional<Chatting> findByProposalAndMabcci(Member proposal, Member mabcci);
}
