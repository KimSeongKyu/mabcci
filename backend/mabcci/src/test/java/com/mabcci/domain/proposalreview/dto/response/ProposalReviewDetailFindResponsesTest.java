package com.mabcci.domain.proposalreview.dto.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProposalReviewDetailFindResponsesTest {

    private ProposalReviewDetailFindResponse proposalReviewDetailFindResponse;
    private ProposalReviewDetailFindResponses proposalReviewDetailFindResponses;

    @BeforeEach
    void setUp() {
        proposalReviewDetailFindResponse =
                new ProposalReviewDetailFindResponse(PICTURE, NICKNAME, 0, "내용", LocalDateTime.now());
        proposalReviewDetailFindResponses = new ProposalReviewDetailFindResponses(List.of(proposalReviewDetailFindResponse));
    }

    @DisplayName("ProposalReviewDetailFindResponses 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(proposalReviewDetailFindResponses).isNotNull(),
                () -> assertThat(proposalReviewDetailFindResponses).isExactlyInstanceOf(ProposalReviewDetailFindResponses.class)
        );
    }

    @DisplayName("ProposalReviewDetailFindResponses 인스턴스 getter 메서드 테스트")
    @Test
    void getter_test() {
        assertThat(proposalReviewDetailFindResponses.proposalReviews())
                .contains(proposalReviewDetailFindResponse);
    }
}
