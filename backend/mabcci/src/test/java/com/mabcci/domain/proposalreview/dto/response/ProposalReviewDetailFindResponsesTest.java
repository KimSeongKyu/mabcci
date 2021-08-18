package com.mabcci.domain.proposalreview.dto.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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

    @DisplayName("ProposalReviewDetailFindResponses 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final ProposalReviewDetailFindResponses invalidResponses = new ProposalReviewDetailFindResponses(null);

        final Set<ConstraintViolation<ProposalReviewDetailFindResponses>> invalidPropertiesOfValidResponse =
                validator.validate(proposalReviewDetailFindResponses);
        final Set<ConstraintViolation<ProposalReviewDetailFindResponses>> invalidPropertiesOfInvalidResponse =
                validator.validate(invalidResponses);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidResponse.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidResponse.size()).isEqualTo(1)
        );
    }
}
