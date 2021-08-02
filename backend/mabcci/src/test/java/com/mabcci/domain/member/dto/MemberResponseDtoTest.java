package com.mabcci.domain.member.dto;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.member.domain.MemberSpecs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static com.mabcci.domain.member.domain.MemberSpecsTest.MEMBER_SPECS;
import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static com.mabcci.domain.model.EmailTest.EMAIL;
import static com.mabcci.domain.model.NicknameTest.NICKNAME;
import static com.mabcci.domain.model.PasswordTest.PASSWORD;
import static com.mabcci.domain.model.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberResponseDtoTest {

    private static final Long ID = 1L;

    private Member member;

    @BeforeEach
    public void setUp() {
        member = Member.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(Gender.MALE)
                .role(MemberRole.USER)
                .build();
        member.updateMemberSpecs(MEMBER_SPECS);
        ReflectionTestUtils.setField(member, "id", ID);
    }

    @DisplayName("MemberResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {

        final MemberResponseDto memberResponseDto = new MemberResponseDto(member);

        assertAll(
                () -> assertThat(memberResponseDto).isNotNull(),
                () -> assertThat(memberResponseDto).isExactlyInstanceOf(MemberResponseDto.class)
        );
    }

    @DisplayName("MemberResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        final MemberResponseDto memberResponseDto = new MemberResponseDto(member);
        assertAll(
                () -> assertThat(memberResponseDto.getId()).isEqualTo(ID),
                () -> assertThat(memberResponseDto.getEmail()).isEqualTo(EMAIL),
                () -> assertThat(memberResponseDto.getNickname()).isEqualTo(NICKNAME),
                () -> assertThat(memberResponseDto.getRole()).isEqualTo(MemberRole.USER)
        );
    }
}