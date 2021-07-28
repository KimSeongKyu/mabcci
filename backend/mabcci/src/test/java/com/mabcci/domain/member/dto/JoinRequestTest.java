package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.domain.member.domain.Gender.MALE;
import static com.mabcci.domain.model.EmailTest.EMAIL;
import static com.mabcci.domain.model.NicknameTest.NICKNAME;
import static com.mabcci.domain.model.PasswordTest.PASSWORD;
import static com.mabcci.domain.model.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class JoinRequestTest {

    @DisplayName("JoinRequestDto 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        final JoinRequest joinRequest = new JoinRequest(EMAIL, PASSWORD, NICKNAME, PHONE, MALE);

        assertAll(
                () -> assertThat(joinRequest).isNotNull(),
                () -> assertThat(joinRequest).isExactlyInstanceOf(JoinRequest.class)
        );

    }

    @DisplayName("JoinRequestDto 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        final JoinRequest joinRequest = new JoinRequest(EMAIL, PASSWORD, NICKNAME, PHONE, MALE);

        assertAll(
                () -> assertThat(joinRequest.getEmail()).isEqualTo(EMAIL),
                () -> assertThat(joinRequest.getPassword()).isEqualTo(PASSWORD),
                () -> assertThat(joinRequest.getNickname()).isEqualTo(NICKNAME),
                () -> assertThat(joinRequest.getPhone()).isEqualTo(PHONE),
                () -> assertThat(joinRequest.getGender()).isEqualTo(MALE)
        );

    }

    @DisplayName("JoinRequestDto 인스턴스 Member 엔티티로 변환 메서드 테스트")
    @Test
    void entity_test() {
        final JoinRequest joinRequest = new JoinRequest(EMAIL, PASSWORD, NICKNAME, PHONE, MALE);
        final Member member = joinRequest.entity();

        assertAll(
                () -> assertThat(member).isNotNull(),
                () -> assertThat(member).isExactlyInstanceOf(Member.class),
                () -> assertThat(member.nickname()).isEqualTo(NICKNAME)
        );

    }
}