package com.mabcci.domain.member.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberDeleteRequestTest {

    @DisplayName("MemberDeleteRequestDto 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        final MemberDeleteRequest memberDeleteRequest = new MemberDeleteRequest(NICKNAME, PASSWORD);
        assertAll(
                () -> assertThat(memberDeleteRequest).isNotNull(),
                () -> assertThat(memberDeleteRequest).isExactlyInstanceOf(MemberDeleteRequest.class)
        );
    }

    @DisplayName("MemberDeleteRequestDto 인스턴스 getter 메서드 테스트")
    @Test
    void getter_test() {
        final MemberDeleteRequest memberDeleteRequest = new MemberDeleteRequest(NICKNAME, PASSWORD);
        assertAll(
                () -> assertThat(memberDeleteRequest.getNickname()).isEqualTo(NICKNAME),
                () -> assertThat(memberDeleteRequest.getPassword()).isEqualTo(PASSWORD)
        );
    }


}