package com.mabcci.domain.member.dto.request;

import com.mabcci.domain.member.dto.request.MemberUpdateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.domain.member.domain.Gender.MAN;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberUpdateRequestTest {

//    @DisplayName("MemberUpdateDto 인스턴스 생성 여부 테스트")
//    @Test
//    void initialize() {
//        final MemberUpdateRequest memberUpdateRequest = new MemberUpdateRequest(NICKNAME, MAN);
//
//        assertAll(
//                () -> assertThat(memberUpdateRequest).isNotNull(),
//                () -> assertThat(memberUpdateRequest).isExactlyInstanceOf(MemberUpdateRequest.class)
//        );
//    }
}