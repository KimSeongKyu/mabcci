package com.mabcci.domain.member.dto.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MemberFindByNicknameContainsResponsesTest {

    private MemberFindByNicknameContainsResponses memberFindByNicknameContainsResponses;
    private MemberFindByNicknameContainsResponse memberFindByNicknameContainsResponse;

    @BeforeEach
    void setUp() {
        memberFindByNicknameContainsResponse = new MemberFindByNicknameContainsResponse(NICKNAME, "testMemberPicture");
        memberFindByNicknameContainsResponses =
                new MemberFindByNicknameContainsResponses(List.of(memberFindByNicknameContainsResponse));
    }

    @DisplayName("MemberFindByNicknameContainsResponses 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(memberFindByNicknameContainsResponses).isNotNull(),
                () -> assertThat(memberFindByNicknameContainsResponses)
                        .isExactlyInstanceOf(MemberFindByNicknameContainsResponses.class)
        );

    }
}
