package com.mabcci.domain.member.dto.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberFindByNicknameContainsResponseTest {

    private MemberFindByNicknameContainsResponse memberFindByNickNameContainsResponse;

    @BeforeEach
    void setUp() {
        memberFindByNickNameContainsResponse = new MemberFindByNicknameContainsResponse(NICKNAME, "testMemberPicture");
    }

    @DisplayName("MemberFindByNickNameContainsResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(memberFindByNickNameContainsResponse).isNotNull(),
                () -> assertThat(memberFindByNickNameContainsResponse)
                        .isExactlyInstanceOf(MemberFindByNicknameContainsResponse.class)
        );
    }
}
