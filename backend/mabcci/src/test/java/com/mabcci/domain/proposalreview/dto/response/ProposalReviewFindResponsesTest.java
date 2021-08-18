package com.mabcci.domain.proposalreview.dto.response;

import com.mabcci.domain.proposalreview.domain.StarRating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

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

    @DisplayName("ProposalReviewFindResponses 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final ProposalReviewFindResponses invalidResponses = new ProposalReviewFindResponses(null);

        final Set<ConstraintViolation<ProposalReviewFindResponses>> invalidPropertiesOfValidResponses =
                validator.validate(proposalReviewFindResponses);
        final Set<ConstraintViolation<ProposalReviewFindResponses>> invalidPropertiesOfInvalidResponses =
                validator.validate(invalidResponses);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidResponses.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidResponses.size()).isEqualTo(1)
        );
    }
}
