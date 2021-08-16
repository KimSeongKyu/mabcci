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

class MemberFindByNicknameContainsResponseTest {

    private MemberFindByNicknameContainsResponse memberFindByNickNameContainsResponse;
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
        memberFindByNickNameContainsResponse = MemberFindByNicknameContainsResponse.ofMember(member);
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
