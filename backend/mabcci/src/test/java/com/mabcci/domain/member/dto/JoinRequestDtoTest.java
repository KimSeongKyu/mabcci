package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JoinRequestDtoTest {

    private String email = "sample@email.com";
    private String password = "password";
    private String nickname = "nickname";
    private String phone = "01012345678";
    private Gender gender = Gender.MALE;

    @DisplayName("MemberSaveDto 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        // when
        JoinRequestDto joinRequestDto = new JoinRequestDto(email, password, nickname, phone, gender);

        // then
        assertAll(
                () -> assertThat(joinRequestDto).isNotNull(),
                () -> assertThat(joinRequestDto).isExactlyInstanceOf(JoinRequestDto.class)
        );

    }

    @DisplayName("MemberSaveDto 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        // given
        JoinRequestDto joinRequestDto = new JoinRequestDto(email, password, nickname, phone, gender);

        // when and then
        assertAll(
                () -> assertThat(joinRequestDto.getEmail()).isEqualTo(email),
                () -> assertThat(joinRequestDto.getPassword()).isEqualTo(password),
                () -> assertThat(joinRequestDto.getNickname()).isEqualTo(nickname),
                () -> assertThat(joinRequestDto.getPhone()).isEqualTo(phone),
                () -> assertThat(joinRequestDto.getGender()).isEqualTo(gender)
        );

    }

    @DisplayName("MemberSaveDto 인스턴스 Member 엔티티로 변환 메서드 테스트")
    @Test
    void entity_test() {
        // given
        JoinRequestDto joinRequestDto = new JoinRequestDto(email, password, nickname, phone, gender);

        // when
        Member member = joinRequestDto.entity();

        // then
        assertAll(
                () -> assertThat(member).isNotNull(),
                () -> assertThat(member).isExactlyInstanceOf(Member.class),
                () -> assertThat(member.nickname()).isEqualTo(nickname)
        );

    }
}