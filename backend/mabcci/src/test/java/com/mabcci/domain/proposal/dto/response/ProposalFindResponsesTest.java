package com.mabcci.domain.proposal.dto.response;

import com.mabcci.global.common.Nickname;
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

class ProposalFindResponsesTest {

    private ProposalFindResponse proposalFindResponse;
    private ProposalFindResponses proposalFindResponses;

    @BeforeEach
    void setUp() {
        proposalFindResponse = new ProposalFindResponse(PICTURE, NICKNAME, LocalDateTime.now());
        proposalFindResponses = new ProposalFindResponses(List.of(proposalFindResponse));
    }

    @DisplayName("ProposalFindResponses 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(proposalFindResponses).isNotNull(),
                () -> assertThat(proposalFindResponses).isExactlyInstanceOf(ProposalFindResponses.class)
        );
    }

    @DisplayName("ProposalFindResponses 인스턴스 getter 메서드 테스트")
    @Test
    void getter_test() {
        assertThat(proposalFindResponses.proposals()).contains(proposalFindResponse);
    }


    @DisplayName("ProposalFindResponses 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final ProposalFindResponses invalidResponse = new ProposalFindResponses(null);

        final Set<ConstraintViolation<ProposalFindResponses>> invalidPropertiesOfValidResponse =
                validator.validate(proposalFindResponses);
        final Set<ConstraintViolation<ProposalFindResponses>> invalidPropertiesOfInvalidResponse =
                validator.validate(invalidResponse);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidResponse.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidResponse.size()).isEqualTo(1)
        );
    }
}
