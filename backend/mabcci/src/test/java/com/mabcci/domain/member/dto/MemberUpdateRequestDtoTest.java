package com.mabcci.domain.member.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.domain.member.domain.Gender.MALE;
import static com.mabcci.domain.model.NicknameTest.NICKNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberUpdateRequestDtoTest {

    @DisplayName("MemberUpdateDto 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        final MemberUpdateRequestDto memberUpdateRequestDto = new MemberUpdateRequestDto(NICKNAME, MALE);

        assertAll(
                () -> assertThat(memberUpdateRequestDto).isNotNull(),
                () -> assertThat(memberUpdateRequestDto).isExactlyInstanceOf(MemberUpdateRequestDto.class)
        );
    }
}