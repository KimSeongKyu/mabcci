package com.mabcci.domain.proposal.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.domain.picture.common.PictureUtilTest.PICTURE_FILES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProposalSaveRequestTest {

    private ProposalSaveRequest proposalSaveRequest;

    @BeforeEach
    void setUp() {
        proposalSaveRequest = new ProposalSaveRequest(1L, 2L, PICTURE_FILES.get(0), null, null, null, null);
    }

    @DisplayName("ProposalSaveRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(proposalSaveRequest).isNotNull(),
                () -> assertThat(proposalSaveRequest).isExactlyInstanceOf(ProposalSaveRequest.class)
        );
    }

    @DisplayName("ProposalSaveRequest 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(proposalSaveRequest.targetMemberId()).isEqualTo(1L),
                () -> assertThat(proposalSaveRequest.mabcciId()).isEqualTo(2L),
                () -> assertThat(proposalSaveRequest.top()).isEqualTo(PICTURE_FILES.get(0)),
                () -> assertThat(proposalSaveRequest.bottom()).isNull(),
                () -> assertThat(proposalSaveRequest.shoes()).isNull(),
                () -> assertThat(proposalSaveRequest.accessory()).isNull(),
                () -> assertThat(proposalSaveRequest.description()).isNull()
        );
    }
}
