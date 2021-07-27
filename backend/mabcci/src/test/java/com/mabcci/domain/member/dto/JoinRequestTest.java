package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JoinRequestTest {

    private static final String EMAIL = "sample@email.com";
    private static final String PASSWORD = "password";
    private static final String NICKNAME = "nickname";
    private static final String PHONE = "01012345678";
    private static final Gender GENDER = Gender.MALE;

    @DisplayName("JoinRequestDto 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        // when
        JoinRequest joinRequest = new JoinRequest(EMAIL, PASSWORD, NICKNAME, PHONE, GENDER);

        // then
        assertAll(
                () -> assertThat(joinRequest).isNotNull(),
                () -> assertThat(joinRequest).isExactlyInstanceOf(JoinRequest.class)
        );

    }

    @DisplayName("JoinRequestDto 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        // given
        JoinRequest joinRequest = new JoinRequest(EMAIL, PASSWORD, NICKNAME, PHONE, GENDER);

        // when and then
        assertAll(
                () -> assertThat(joinRequest.getEmail()).isEqualTo(EMAIL),
                () -> assertThat(joinRequest.getPassword()).isEqualTo(PASSWORD),
                () -> assertThat(joinRequest.getNickname()).isEqualTo(NICKNAME),
                () -> assertThat(joinRequest.getPhone()).isEqualTo(PHONE),
                () -> assertThat(joinRequest.getGender()).isEqualTo(GENDER)
        );

    }

    @DisplayName("JoinRequestDto 인스턴스 Member 엔티티로 변환 메서드 테스트")
    @Test
    void entity_test() {
        // given
        JoinRequest joinRequest = new JoinRequest(EMAIL, PASSWORD, NICKNAME, PHONE, GENDER);

        // when
        Member member = joinRequest.entity();

        // then
        assertAll(
                () -> assertThat(member).isNotNull(),
                () -> assertThat(member).isExactlyInstanceOf(Member.class),
                () -> assertThat(member.nickname()).isEqualTo(NICKNAME)
        );

    }
}