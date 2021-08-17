package com.mabcci.domain.proposal.dto.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProposalFindResponseTest {

    private ProposalFindResponse proposalFindResponse;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        now = LocalDateTime.now();
        proposalFindResponse = new ProposalFindResponse(PICTURE, NICKNAME, now);
    }

    @DisplayName("ProposalFindResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(proposalFindResponse).isNotNull(),
                () -> assertThat(proposalFindResponse).isExactlyInstanceOf(ProposalFindResponse.class)
        );
    }

    @DisplayName("ProposalFindResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(proposalFindResponse.picture()).isEqualTo(PICTURE),
                () -> assertThat(proposalFindResponse.nickname()).isEqualTo(NICKNAME),
                () -> assertThat(proposalFindResponse.createdDate()).isEqualTo(now)
        );
    }
}
