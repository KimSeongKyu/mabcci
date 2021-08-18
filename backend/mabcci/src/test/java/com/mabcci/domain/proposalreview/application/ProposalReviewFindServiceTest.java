package com.mabcci.domain.proposalreview.application;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.proposal.domain.Proposal;
import com.mabcci.domain.proposalreview.domain.ProposalReview;
import com.mabcci.domain.proposalreview.domain.ProposalReviewRepository;
import com.mabcci.domain.proposalreview.domain.StarRating;
import com.mabcci.domain.proposalreview.dto.response.ProposalReviewFindResponse;
import com.mabcci.domain.proposalreview.dto.response.ProposalReviewFindResponses;
import com.mabcci.global.common.Email;
import com.mabcci.global.common.Nickname;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
class ProposalReviewFindServiceTest {

    @InjectMocks private ProposalReviewFindService proposalReviewFindService;
    @Mock private ProposalReviewRepository proposalReviewRepository;

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

    @DisplayName("ProposalReviewFindService 인스턴스 제안서에 해당하는 리뷰 상세 조회 테스트")
    @Test
    void find_proposal_review_by_proposal_id_test() {
        doReturn(Optional.of(proposalReview)).when(proposalReviewRepository).findByProposalId(any());

        final ProposalReviewFindResponse proposalReviewFindResponse =
                proposalReviewFindService.findProposalReviewByProposalId(proposal.id());

        verify(proposalReviewRepository, times(1)).findByProposalId(any());

        assertAll(
                () -> assertThat(proposalReviewFindResponse.starRating()).isEqualTo(proposalReview.starRating().ordinal()),
                () -> assertThat(proposalReviewFindResponse.content()).isEqualTo(proposalReview.content())
        );
    }

    @DisplayName("ProposalReviewFindService 인스턴스 맵씨 닉네임에 해당하는 최근 제안서 리뷰 3개 조회 테스트")
    @Test
    void find_lately_three_proposal_reviews_by_nickname_test() {
        doReturn(List.of(proposalReview)).when(proposalReviewRepository).findLatelyThreeByNickname(any(), any());

        final ProposalReviewFindResponses proposalReviewFindResponses =
                proposalReviewFindService.findLatelyThreeProposalReviewsByNickname(proposal.mabcci().nickname());

        verify(proposalReviewRepository, times(1)).findLatelyThreeByNickname(any(), any());

        assertThat(proposalReviewFindResponses.proposalReviews())
                .contains(ProposalReviewFindResponse.ofProposalReview(proposalReview));
    }
}
