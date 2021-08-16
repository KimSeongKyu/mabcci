package com.mabcci.domain.member.dto.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberFindByNicknameContainsResponsesTest {

    private MemberFindByNicknameContainsResponses memberFindByNicknameContainsResponses;
    private MemberFindByNicknameContainsResponse memberFindByNicknameContainsResponse;

    @BeforeEach
    void setUp() {
        memberFindByNicknameContainsResponse = new MemberFindByNicknameContainsResponse(NICKNAME, "testMemberPicture");
        memberFindByNicknameContainsResponses =
                new MemberFindByNicknameContainsResponses(List.of(memberFindByNicknameContainsResponse));
    }

    @DisplayName("MemberFindByNicknameContainsResponses 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(memberFindByNicknameContainsResponses).isNotNull(),
                () -> assertThat(memberFindByNicknameContainsResponses)
                        .isExactlyInstanceOf(MemberFindByNicknameContainsResponses.class)
        );
    }

    @DisplayName("MemberFindByNicknameContainsResponses 인스턴스 getter 메서드 테스트")
    @Test
    void getter_test() {
        assertThat(memberFindByNicknameContainsResponses.members())
                .isEqualTo(List.of(memberFindByNicknameContainsResponse));
    }

    @DisplayName("MemberFindByNicknameContainsResponses 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final MemberFindByNicknameContainsResponses invalidResponses =
                new MemberFindByNicknameContainsResponses(null);

        final Set<ConstraintViolation<MemberFindByNicknameContainsResponses>> invalidPropertiesOfValidResponse =
                validator.validate(memberFindByNicknameContainsResponses);
        final Set<ConstraintViolation<MemberFindByNicknameContainsResponses>> invalidPropertiesOfInvalidResponse =
                validator.validate(invalidResponses);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidResponse.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidResponse.size()).isEqualTo(1)
        );
    }
}
