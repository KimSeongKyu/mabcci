package com.mabcci.domain.proposalreview.dto.response;

import com.mabcci.domain.proposalreview.domain.StarRating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProposalReviewFindResponsesTest {

    private ProposalReviewFindResponses proposalReviewFindResponses;
    private ProposalReviewFindResponse proposalReviewFindResponse;

    @BeforeEach
    void setUp() {
        proposalReviewFindResponse = new ProposalReviewFindResponse(StarRating.ZERO.ordinal(), "내용");
        proposalReviewFindResponses = new ProposalReviewFindResponses(List.of(proposalReviewFindResponse));
    }

    @DisplayName("ProposalReviewFindResponses 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(proposalReviewFindResponses).isNotNull(),
                () -> assertThat(proposalReviewFindResponses).isExactlyInstanceOf(ProposalReviewFindResponses.class)
        );
    }

    @DisplayName("ProposalReviewFindResponses 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertThat(proposalReviewFindResponses.proposalReviews()).contains(proposalReviewFindResponse);
    }
}
