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

class MemberFindSimpleResponsesTest {

    private MemberFindSimpleResponses memberFindSimpleResponses;
    private MemberFindSimpleResponse memberFindSimpleResponse;

    @BeforeEach
    void setUp() {
        memberFindSimpleResponse = new MemberFindSimpleResponse(NICKNAME, "testMemberPicture");
        memberFindSimpleResponses =
                new MemberFindSimpleResponses(List.of(memberFindSimpleResponse));
    }

    @DisplayName("MemberFindSimpleResponses 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(memberFindSimpleResponses).isNotNull(),
                () -> assertThat(memberFindSimpleResponses)
                        .isExactlyInstanceOf(MemberFindSimpleResponses.class)
        );
    }

    @DisplayName("MemberFindSimpleResponses 인스턴스 getter 메서드 테스트")
    @Test
    void getter_test() {
        assertThat(memberFindSimpleResponses.members())
                .isEqualTo(List.of(memberFindSimpleResponse));
    }

    @DisplayName("MemberFindSimpleResponses 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final MemberFindSimpleResponses invalidResponses =
                new MemberFindSimpleResponses(null);

        final Set<ConstraintViolation<MemberFindSimpleResponses>> invalidPropertiesOfValidResponse =
                validator.validate(memberFindSimpleResponses);
        final Set<ConstraintViolation<MemberFindSimpleResponses>> invalidPropertiesOfInvalidResponse =
                validator.validate(invalidResponses);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidResponse.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidResponse.size()).isEqualTo(1)
        );
    }
}
