package com.mabcci.domain.member.dto.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberFindByNicknameContainsResponseTest {

    private MemberFindByNicknameContainsResponse memberFindByNickNameContainsResponse;

    @BeforeEach
    void setUp() {
        memberFindByNickNameContainsResponse = new MemberFindByNicknameContainsResponse(NICKNAME, "testMemberPicture");
    }

    @DisplayName("MemberFindByNickNameContainsResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(memberFindByNickNameContainsResponse).isNotNull(),
                () -> assertThat(memberFindByNickNameContainsResponse)
                        .isExactlyInstanceOf(MemberFindByNicknameContainsResponse.class)
        );
    }

    @DisplayName("MemberFindByNickNameContainsResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(memberFindByNickNameContainsResponse.nickname()).isEqualTo(NICKNAME),
                () -> assertThat(memberFindByNickNameContainsResponse.picture()).isEqualTo("testMemberPicture")
        );
    }

    @DisplayName("MemberFindByNickNameContainsResponse 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final MemberFindByNicknameContainsResponse invalidResponse =
                new MemberFindByNicknameContainsResponse(null, "");

        final Set<ConstraintViolation<MemberFindByNicknameContainsResponse>> invalidPropertiesOfValidResponse =
                validator.validate(memberFindByNickNameContainsResponse);
        final Set<ConstraintViolation<MemberFindByNicknameContainsResponse>> invalidPropertiesOfInvalidResponse =
                validator.validate(invalidResponse);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidResponse.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidResponse.size()).isEqualTo(1)
        );
    }
}
