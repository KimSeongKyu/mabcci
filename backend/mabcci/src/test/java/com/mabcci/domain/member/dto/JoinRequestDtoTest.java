package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JoinRequestDtoTest {

    private static final String EMAIL = "sample@email.com";
    private static final String PASSWORD = "password";
    private static final String NICKNAME = "nickname";
    private static final String PHONE = "01012345678";
    private static final Gender GENDER = Gender.MALE;

    @DisplayName("JoinRequestDto 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        // when
        JoinRequestDto joinRequestDto = new JoinRequestDto(EMAIL, PASSWORD, NICKNAME, PHONE, GENDER);

        // then
        assertAll(
                () -> assertThat(joinRequestDto).isNotNull(),
                () -> assertThat(joinRequestDto).isExactlyInstanceOf(JoinRequestDto.class)
        );

    }

    @DisplayName("JoinRequestDto 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        // given
        JoinRequestDto joinRequestDto = new JoinRequestDto(EMAIL, PASSWORD, NICKNAME, PHONE, GENDER);

        // when and then
        assertAll(
                () -> assertThat(joinRequestDto.getEmail()).isEqualTo(EMAIL),
                () -> assertThat(joinRequestDto.getPassword()).isEqualTo(PASSWORD),
                () -> assertThat(joinRequestDto.getNickname()).isEqualTo(NICKNAME),
                () -> assertThat(joinRequestDto.getPhone()).isEqualTo(PHONE),
                () -> assertThat(joinRequestDto.getGender()).isEqualTo(GENDER)
        );

    }

    @DisplayName("JoinRequestDto 인스턴스 Member 엔티티로 변환 메서드 테스트")
    @Test
    void entity_test() {
        // given
        JoinRequestDto joinRequestDto = new JoinRequestDto(EMAIL, PASSWORD, NICKNAME, PHONE, GENDER);

        // when
        Member member = joinRequestDto.entity();

        // then
        assertAll(
                () -> assertThat(member).isNotNull(),
                () -> assertThat(member).isExactlyInstanceOf(Member.class),
                () -> assertThat(member.nickname()).isEqualTo(NICKNAME)
        );

    }
}