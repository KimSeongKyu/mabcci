package com.mabcci.domain.member.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    private Member member;

    @BeforeEach
    void setUp() {
        member = Member.builder()
                .email("sample@email.com")
                .password("validPassword")
                .nickname("sample")
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
        String validPassword = "validPassword";
        String invalidPassword = "invalidPassword";

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
                () -> assertThat(member.nickname()).isEqualTo("sample"),
                () -> assertThat(member.role()).isEqualTo(MemberRole.USER),
                () -> assertThat(member.password()).isEqualTo("validPassword")
        );
    }

}