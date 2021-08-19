package com.mabcci.domain.member.dto.response;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberFindSimpleResponseTest {

    private MemberFindSimpleResponse memberFindSimpleResponse;
    private Member member;

    @BeforeEach
    void setUp() {
        member = Member.Builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(Gender.MAN)
                .description(DESCRIPTION)
                .picture(PICTURE)
                .memberRole(MemberRole.USER)
                .build();
        memberFindSimpleResponse = MemberFindSimpleResponse.ofMember(member);
    }

    @DisplayName("memberFindSimpleResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(memberFindSimpleResponse).isNotNull(),
                () -> assertThat(memberFindSimpleResponse)
                        .isExactlyInstanceOf(MemberFindSimpleResponse.class)
        );
    }

    @DisplayName("memberFindSimpleResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(memberFindSimpleResponse.nickname()).isEqualTo(NICKNAME),
                () -> assertThat(memberFindSimpleResponse.picture()).isEqualTo(PICTURE)
        );
    }

    @DisplayName("memberFindSimpleResponse 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final MemberFindSimpleResponse invalidResponse =
                new MemberFindSimpleResponse(null, "");

        final Set<ConstraintViolation<MemberFindSimpleResponse>> invalidPropertiesOfValidResponse =
                validator.validate(memberFindSimpleResponse);
        final Set<ConstraintViolation<MemberFindSimpleResponse>> invalidPropertiesOfInvalidResponse =
                validator.validate(invalidResponse);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidResponse.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidResponse.size()).isEqualTo(1)
        );
    }
}
