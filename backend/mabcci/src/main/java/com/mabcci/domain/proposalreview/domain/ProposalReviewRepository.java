package com.mabcci.domain.proposalreview.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProposalReviewRepository extends JpaRepository<ProposalReview, Long> {

    @Query("SELECT p_r " +
            "FROM ProposalReview p_r " +
            "JOIN FETCH p_r.proposal p " +
            "WHERE p.id = :proposalId")
    Optional<ProposalReview> findByProposalId(@Param("proposalId") Long proposalId);
}
