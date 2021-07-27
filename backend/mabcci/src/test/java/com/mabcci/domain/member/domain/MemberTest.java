package com.mabcci.domain.member.domain;

import com.mabcci.domain.model.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static com.mabcci.domain.model.EmailTest.EMAIL;
import static com.mabcci.domain.model.NicknameTest.NICKNAME;
import static com.mabcci.domain.model.PasswordTest.PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    private Member member;

    @BeforeEach
    void setUp() {
        member = Member.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone("010-1234-5678")
                .gender(Gender.MALE)
                .role(MemberRole.USER)
                .build();
    }

    @DisplayName("Member 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        // when and then
        assertAll(
                () -> assertThat(member).isNotNull(),
                () -> assertThat(member).isExactlyInstanceOf(Member.class)
        );

    }

    @DisplayName("Member 인스턴스의 password 일치 여부 테스트")
    @Test
    void passwordCheck_test() {
        // given
        Password validPassword = PASSWORD;
        Password invalidPassword = Password.of("invalidPassword");

        // when and then
        assertAll(
                () -> assertThat(member.checkPassword(validPassword)).isTrue(),
                () -> assertThat(member.checkPassword(invalidPassword)).isFalse()
        );
    }

    @DisplayName("Member 인스턴스의 getter 메서드들 테스트")
    @Test
    void getter_test() {
        // given
        ReflectionTestUtils.setField(member, "id", 1L);

        // when and then
        assertAll(
                () -> assertThat(member.id()).isEqualTo(1L),
                () -> assertThat(member.nickname()).isEqualTo(NICKNAME),
                () -> assertThat(member.role()).isEqualTo(MemberRole.USER)
        );
    }

}