package com.mabcci.member.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @DisplayName("Member 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        Member member = Member.builder()
                .email("sample@email.com")
                .password("samplePassword")
                .nickname("sample")
                .phone("010-1234-5678")
                .gender("male")
                .role(MemberRole.General);

        assertAll(
                () -> Assertions.assertThat(member).isNotNull(),
                () -> Assertions.assertThat(member).isExactlyInstanceOf(Member.class)
        );

    }

}