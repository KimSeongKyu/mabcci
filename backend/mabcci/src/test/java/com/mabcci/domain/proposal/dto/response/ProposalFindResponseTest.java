package com.mabcci.domain.proposal.dto.response;

import com.mabcci.global.common.Nickname;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.Set;

import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProposalFindResponseTest {

    private ProposalFindResponse proposalFindResponse;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        now = LocalDateTime.now();
        proposalFindResponse = new ProposalFindResponse(1L, PICTURE, NICKNAME, now, false);
    }

    @DisplayName("ProposalFindResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(proposalFindResponse).isNotNull(),
                () -> assertThat(proposalFindResponse).isExactlyInstanceOf(ProposalFindResponse.class)
        );
    }

    @DisplayName("ProposalFindResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(proposalFindResponse.picture()).isEqualTo(PICTURE),
                () -> assertThat(proposalFindResponse.nickname()).isEqualTo(NICKNAME),
                () -> assertThat(proposalFindResponse.createdDate()).isEqualTo(now),
                () -> assertThat(proposalFindResponse.isReviewed()).isFalse()
        );
    }

    @DisplayName("ProposalFindResponse 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final ProposalFindResponse invalidResponse = new ProposalFindResponse(0L, null, Nickname.of(""), LocalDateTime.MAX, null);

        final Set<ConstraintViolation<ProposalFindResponse>> invalidPropertiesOfValidResponse =
                validator.validate(proposalFindResponse);
        final Set<ConstraintViolation<ProposalFindResponse>> invalidPropertiesOfInvalidResponse =
                validator.validate(invalidResponse);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidResponse.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidResponse.size()).isEqualTo(4)
        );
    }
}
