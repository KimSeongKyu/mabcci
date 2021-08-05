package com.mabcci.domain.member.dto.response;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.member.dto.response.MemberResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static com.mabcci.domain.member.domain.MemberSpecsTest.MEMBER_SPECS;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberResponseTest {

    private static final Long ID = 1L;

    private Member member;

    @BeforeEach
    public void setUp() {
        member = Member.Builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(Gender.MAN)
                .memberRole(MemberRole.USER)
                .build();
        member.updateMemberSpecs(MEMBER_SPECS);
        ReflectionTestUtils.setField(member, "id", ID);
    }

    @DisplayName("MemberResponse 인스턴스 기본 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {

        final MemberResponse memberResponse = new MemberResponse();

        assertAll(
                () -> assertThat(memberResponse).isNotNull(),
                () -> assertThat(memberResponse).isExactlyInstanceOf(MemberResponse.class)
        );
    }

    @DisplayName("MemberResponse 인스턴스 생성 여부 테스트")
    @Test
    void constructor_test() {

        final MemberResponse memberResponse = new MemberResponse(member);

        assertAll(
                () -> assertThat(memberResponse).isNotNull(),
                () -> assertThat(memberResponse).isExactlyInstanceOf(MemberResponse.class)
        );
    }

    @DisplayName("MemberResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        final MemberResponse memberResponse = new MemberResponse(member);
        assertAll(
                () -> assertThat(memberResponse.getId()).isEqualTo(ID),
                () -> assertThat(memberResponse.getEmail()).isEqualTo(EMAIL),
                () -> assertThat(memberResponse.getNickname()).isEqualTo(NICKNAME),
                () -> assertThat(memberResponse.getRole()).isEqualTo(MemberRole.USER)
        );
    }
}