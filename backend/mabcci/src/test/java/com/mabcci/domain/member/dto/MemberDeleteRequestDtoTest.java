package com.mabcci.domain.member.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberDeleteRequestDtoTest {

    private static final String NICKNAME = "nickname";
    private static final String PASSWORD = "password";

    @DisplayName("MemberDeleteRequestDto 인스턴스 생성 여부")
    @Test
    void initialize() {
        MemberDeleteRequestDto memberDeleteRequestDto = new MemberDeleteRequestDto(NICKNAME, PASSWORD);
        assertAll(
                () -> assertThat(memberDeleteRequestDto).isNotNull(),
                () -> assertThat(memberDeleteRequestDto).isExactlyInstanceOf(MemberUpdateRequestDto.class)
        );
    }

}