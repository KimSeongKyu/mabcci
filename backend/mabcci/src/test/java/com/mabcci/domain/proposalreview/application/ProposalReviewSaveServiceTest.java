package com.mabcci.domain.proposalreview.application;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.proposal.domain.Proposal;
import com.mabcci.domain.proposal.domain.ProposalRepository;
import com.mabcci.domain.proposalreview.domain.ProposalReview;
import com.mabcci.domain.proposalreview.domain.ProposalReviewRepository;
import com.mabcci.domain.proposalreview.domain.StarRating;
import com.mabcci.domain.proposalreview.dto.request.ProposalReviewSaveRequest;
import com.mabcci.global.common.Email;
import com.mabcci.global.common.Nickname;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProposalReviewSaveServiceTest {

    @InjectMocks private ProposalReviewSaveService proposalReviewSaveService;
    @Mock private ProposalReviewRepository proposalReviewRepository;
    @Mock private ProposalRepository proposalRepository;

    private ProposalReview proposalReview;
    private Proposal proposal;
    private Member targetMember;
    private Member mabcci;

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
        mabcci = Member.Builder()
                .email(Email.of("mabcci@example.com"))
                .password(PASSWORD)
                .nickname(Nickname.of("mabcci"))
                .phone(PHONE)
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
        proposalReview = ProposalReview.builder()
                .proposal(proposal)
                .starRating(StarRating.ZERO)
                .content("내용")
                .build();
    }

    @DisplayName("ProposalReviewSaveService 인스턴스 제안서 리뷰 저장 테스트")
    @Test
    void save_proposal_review_test() {
        doReturn(Optional.of(proposal)).when(proposalRepository).findById(any());
        doReturn(proposalReview).when(proposalReviewRepository).save(any());

        final ProposalReviewSaveRequest proposalReviewSaveRequest =
                new ProposalReviewSaveRequest(proposal.id(), StarRating.ZERO, "내용");

        proposalReviewSaveService.saveProposalReview(proposalReviewSaveRequest);

        verify(proposalRepository, times(1)).findById(any());
        verify(proposalReviewRepository, times(1)).save(any());
    }
}
