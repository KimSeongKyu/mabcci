package com.mabcci.domain.proposal.application;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.proposal.domain.Proposal;
import com.mabcci.domain.proposal.domain.ProposalFilter;
import com.mabcci.domain.proposal.domain.ProposalRepository;
import com.mabcci.domain.proposal.dto.response.ProposalDetailFindResponse;
import com.mabcci.domain.proposal.dto.response.ProposalFindResponse;
import com.mabcci.domain.proposal.dto.response.ProposalFindResponses;
import com.mabcci.global.common.Email;
import com.mabcci.global.common.Nickname;
import com.mabcci.global.common.Phone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProposalFindServiceTest {

    @InjectMocks private ProposalFindService proposalFindService;
    @Mock private ProposalRepository proposalRepository;

    private Member targetMember;
    private Member mabcci;
    private Nickname mabcciNickname;
    private Proposal proposal;

    @BeforeEach
    void setUp() {
        targetMember = Member.Builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(Gender.MAN)
                .description(DESCRIPTION)
                .picture(PICTURE)
                .memberRole(MemberRole.USER)
                .build();
        mabcciNickname = Nickname.of("mabcci");
        mabcci = Member.Builder()
                .email(Email.of("mabcci@example.com"))
                .password(PASSWORD)
                .nickname(mabcciNickname)
                .phone(Phone.of("010-2345-6789"))
                .gender(Gender.MAN)
                .description(DESCRIPTION)
                .picture(PICTURE)
                .memberRole(MemberRole.MABCCI)
                .build();
        proposal = Proposal.builder()
                .targetMember(targetMember)
                .mabcci(mabcci)
                .top("topPictureUrl")
                .bottom("bottomPictureUrl")
                .shoes("shoesPictureUrl")
                .accessory("accessoryPictureUrl")
                .description("description")
                .build();
    }

    @DisplayName("ProposalFindService 인스턴스 내가 제안한 제안서 리스트 조회 테스트")
    @Test
    void find_suggested_proposals_test() {
        doReturn(List.of(proposal)).when(proposalRepository).findAllByMabcciNickname(any());

        final ProposalFindResponses proposalFindResponses = proposalFindService.findProposals(ProposalFilter.SUGGESTED, NICKNAME);
        final ProposalFindResponse proposalFindResponse = proposalFindResponses.proposals().get(0);

        verify(proposalRepository, times(1)).findAllByMabcciNickname(any());
        assertAll(
                () -> assertThat(proposalFindResponse.picture()).isEqualTo(PICTURE),
                () -> assertThat(proposalFindResponse.nickname()).isEqualTo(NICKNAME)
        );
    }
    @DisplayName("ProposalFindService 인스턴스 맵씨들이 나에게 제안한 제안서 리스트 조회 테스트")
    @Test
    void find_received_proposals_test() {
        doReturn(List.of(proposal)).when(proposalRepository).findAllByTargetMemberNickname(any());

        final ProposalFindResponses proposalFindResponses = proposalFindService.findProposals(ProposalFilter.RECEIVED, mabcciNickname);
        final ProposalFindResponse proposalFindResponse = proposalFindResponses.proposals().get(0);

        verify(proposalRepository, times(1)).findAllByTargetMemberNickname(any());
        assertAll(
                () -> assertThat(proposalFindResponse.picture()).isEqualTo(PICTURE),
                () -> assertThat(proposalFindResponse.nickname()).isEqualTo(mabcciNickname)
        );
    }

    @DisplayName("ProposalFindService 인스턴스 제안서 상세 조회 테스트")
    @Test
    void find_proposal_test() {
        ReflectionTestUtils.setField(proposal, "id", 1L);
        doReturn(Optional.of(proposal)).when(proposalRepository).findById(any());

        final ProposalDetailFindResponse proposalDetailFindResponse = proposalFindService.findProposal(proposal.id());

        verify(proposalRepository, times(1)).findById(any());
        assertAll(
                () -> assertThat(proposalDetailFindResponse.targetMemberNickname()).isEqualTo(targetMember.nickname()),
                () -> assertThat(proposalDetailFindResponse.mabcciNickname()).isEqualTo(mabcci.nickname()),
                () -> assertThat(proposalDetailFindResponse.top()).isEqualTo(proposal.top()),
                () -> assertThat(proposalDetailFindResponse.bottom()).isEqualTo(proposal.bottom()),
                () -> assertThat(proposalDetailFindResponse.shoes()).isEqualTo(proposal.shoes()),
                () -> assertThat(proposalDetailFindResponse.accessory()).isEqualTo(proposal.accessory()),
                () -> assertThat(proposalDetailFindResponse.description()).isEqualTo(proposal.description())
        );
    }

}
