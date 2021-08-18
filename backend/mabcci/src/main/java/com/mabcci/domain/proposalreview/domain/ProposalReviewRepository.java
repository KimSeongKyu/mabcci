package com.mabcci.domain.proposalreview.domain;

import com.mabcci.global.common.Nickname;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProposalReviewRepository extends JpaRepository<ProposalReview, Long> {

    @Query("SELECT p_r " +
            "FROM ProposalReview p_r " +
            "JOIN FETCH p_r.proposal p " +
            "WHERE p.id = :proposalId")
    Optional<ProposalReview> findByProposalId(@Param("proposalId") Long proposalId);

    @Query(value = "SELECT p_r " +
            "FROM ProposalReview p_r " +
            "JOIN FETCH p_r.proposal p " +
            "JOIN FETCH p.mabcci m " +
            "WHERE m.nickname = :mabcciNickname " +
            "ORDER BY p_r.id DESC",
    countQuery = "SELECT count(p_r) " +
            "FROM ProposalReview p_r " +
            "JOIN p_r.proposal p " +
            "JOIN p.mabcci m " +
            "WHERE m.nickname = :mabcciNickname " +
            "ORDER BY p_r.id DESC")
    List<ProposalReview> findLatelyThreeByNickname(@Param("mabcciNickname") Nickname mabcciNickname, Pageable pageable);
}
