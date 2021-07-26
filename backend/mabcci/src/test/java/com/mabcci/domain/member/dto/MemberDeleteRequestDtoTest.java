package com.mabcci.domain.member.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberDeleteRequestDtoTest {

    private static final String NICKNAME = "nickname";
    private static final String PASSWORD = "password";

    @DisplayName("MemberDeleteRequestDto 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        MemberDeleteRequestDto memberDeleteRequestDto = new MemberDeleteRequestDto(NICKNAME, PASSWORD);
        assertAll(
                () -> assertThat(memberDeleteRequestDto).isNotNull(),
                () -> assertThat(memberDeleteRequestDto).isExactlyInstanceOf(MemberDeleteRequestDto.class)
        );
    }

    @DisplayName("MemberDeleteRequestDto 인스턴스 getter 메서드 테스트")
    @Test
    void getter_test() {
        MemberDeleteRequestDto memberDeleteRequestDto = new MemberDeleteRequestDto(NICKNAME, PASSWORD);
        assertAll(
                () -> assertThat(memberDeleteRequestDto.getNickname()).isEqualTo(NICKNAME),
                () -> assertThat(memberDeleteRequestDto.getPassword()).isEqualTo(PASSWORD)
        );
    }


}