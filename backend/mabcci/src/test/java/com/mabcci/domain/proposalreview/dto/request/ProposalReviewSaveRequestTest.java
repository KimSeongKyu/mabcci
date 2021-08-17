package com.mabcci.domain.proposalreview.dto.request;

import com.mabcci.domain.proposalreview.domain.StarRating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProposalReviewSaveRequestTest {

    private ProposalReviewSaveRequest proposalReviewSaveRequest;

    @BeforeEach
    void setUp() {
        proposalReviewSaveRequest = new ProposalReviewSaveRequest(1L, StarRating.ZERO, "내용");
    }

    @DisplayName("ProposalReviewSaveRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(proposalReviewSaveRequest).isNotNull(),
                () -> assertThat(proposalReviewSaveRequest).isExactlyInstanceOf(ProposalReviewSaveRequest.class)
        );
    }
}
