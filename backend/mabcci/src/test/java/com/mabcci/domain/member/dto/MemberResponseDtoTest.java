package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.MemberRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.domain.model.EmailTest.EMAIL;
import static com.mabcci.domain.model.NicknameTest.NICKNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberResponseDtoTest {

    private static final Long ID = 1L;
    private static final Gender GENDER = Gender.MALE;
    private static final MemberRole ROLE = MemberRole.USER;

    @DisplayName("MemberResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        final MemberResponseDto memberResponseDto = new MemberResponseDto(ID, EMAIL, NICKNAME, GENDER, ROLE);

        assertAll(
                () -> assertThat(memberResponseDto).isNotNull(),
                () -> assertThat(memberResponseDto).isExactlyInstanceOf(MemberResponseDto.class)
        );
    }

    @DisplayName("MemberResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        final MemberResponseDto memberResponseDto = new MemberResponseDto(ID, EMAIL, NICKNAME, GENDER, ROLE);

        assertAll(
                () -> assertThat(memberResponseDto.getId()).isEqualTo(ID),
                () -> assertThat(memberResponseDto.getEmail()).isEqualTo(EMAIL),
                () -> assertThat(memberResponseDto.getNickname()).isEqualTo(NICKNAME),
                () -> assertThat(memberResponseDto.getRole()).isEqualTo(ROLE)
        );
    }
}