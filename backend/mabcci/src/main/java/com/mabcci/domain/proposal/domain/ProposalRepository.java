package com.mabcci.domain.proposal.domain;

import com.mabcci.global.common.Nickname;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {

    @Query("SELECT p " +
            "FROM Proposal p " +
            "JOIN FETCH p.mabcci m " +
            "WHERE m.nickname = :nickname " +
            "ORDER BY p.id DESC")
    List<Proposal> findAllByMabcciNickname(@Param("nickname") Nickname nickname);

    @Query("SELECT p " +
            "FROM Proposal p " +
            "JOIN FETCH p.targetMember m " +
            "WHERE m.nickname = :nickname " +
            "ORDER BY p.id DESC")
    List<Proposal> findAllByTargetMemberNickname(@Param("nickname") Nickname nickname);
}
