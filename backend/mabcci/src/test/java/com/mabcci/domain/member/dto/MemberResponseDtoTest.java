package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.MemberRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberResponseDtoTest {

    private static final Long ID = 1L;
    private static final String NICKNAME = "nickname";
    private static final MemberRole ROLE = MemberRole.USER;

    @DisplayName("MemberResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        // when
        MemberResponseDto memberResponseDto = new MemberResponseDto(ID, NICKNAME, ROLE);

        // then
        assertAll(
                () -> assertThat(memberResponseDto).isNotNull(),
                () -> assertThat(memberResponseDto).isExactlyInstanceOf(MemberResponseDto.class)
        );
    }

    @DisplayName("MemberResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        // when
        MemberResponseDto memberResponseDto = new MemberResponseDto(ID, NICKNAME, ROLE);

        // then
        assertAll(
                () -> assertThat(memberResponseDto.getId()).isEqualTo(ID),
                () -> assertThat(memberResponseDto.getNickName()).isEqualTo(NICKNAME),
                () -> assertThat(memberResponseDto.getRole()).isEqualTo(ROLE)
        );
    }
}