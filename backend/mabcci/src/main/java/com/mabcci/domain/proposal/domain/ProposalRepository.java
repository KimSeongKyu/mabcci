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
            "WHERE m.nickname = :mabcciNickname")
    List<Proposal> findAllByMabcciNickname(@Param("mabcciNickname") Nickname mabcciNickname);
}
