package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberUpdateRequestDtoTest {

    private static final String NICKNAME = "nickname";
    private static final Gender GENDER = Gender.MALE;

    @DisplayName("MemberUpdateDto 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        // given
        MemberUpdateRequestDto memberUpdateRequestDto = new MemberUpdateRequestDto(NICKNAME, GENDER);

        // then
        assertAll(
                () -> assertThat(memberUpdateRequestDto).isNotNull(),
                () -> assertThat(memberUpdateRequestDto).isExactlyInstanceOf(MemberUpdateRequestDto.class)
        );
    }
}