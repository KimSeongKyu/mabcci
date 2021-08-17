package com.mabcci.domain.proposal.dto.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProposalFindResponsesTest {

    private ProposalFindResponse proposalFindResponse;
    private ProposalFindResponses proposalFindResponses;

    @BeforeEach
    void setUp() {
        proposalFindResponse = new ProposalFindResponse(PICTURE, NICKNAME, LocalDateTime.now());
        proposalFindResponses = new ProposalFindResponse(List.of(proposalFindResponse));
    }

    @DisplayName("ProposalFindResponses 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(proposalFindResponses).isNotNull(),
                () -> assertThat(proposalFindResponses).isExactlyInstanceOf(ProposalFindResponses.class)
        );
    }
}
