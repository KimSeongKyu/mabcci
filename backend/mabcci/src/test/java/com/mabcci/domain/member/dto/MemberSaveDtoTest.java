package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberSaveDtoTest {

    private String email = "sample@email.com";
    private String password = "password";
    private String nickname = "nickname";
    private String phone = "01012345678";
    private Gender gender = Gender.MALE;


    @DisplayName("MemberSaveDto 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        // when
        MemberSaveDto memberSaveDto = new MemberSaveDto(email, password, nickname, phone, gender);

        // then
        assertAll(
                () -> assertThat(memberSaveDto).isNotNull(),
                () -> assertThat(memberSaveDto).isExactlyInstanceOf(MemberSaveDto.class)
        );

    }
}