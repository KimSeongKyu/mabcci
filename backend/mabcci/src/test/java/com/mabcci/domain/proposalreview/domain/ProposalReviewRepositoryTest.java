package com.mabcci.domain.proposalreview.domain;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.proposal.domain.Proposal;
import com.mabcci.global.common.Email;
import com.mabcci.global.common.Nickname;
import com.mabcci.global.common.Phone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.stream.IntStream;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@EnableJpaAuditing
@DataJpaTest
class ProposalReviewRepositoryTest {

    @Autowired private ProposalReviewRepository proposalReviewRepository;
    @Autowired private TestEntityManager testEntityManager;

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
        proposalReview = ProposalReview.builder()
                .proposal(proposal)
                .starRating(StarRating.ZERO)
                .content("내용")
                .build();
        testEntityManager.persist(targetMember);
        testEntityManager.persist(mabcci);
        testEntityManager.persist(proposal);
    }

    @DisplayName("ProposalReviewRepository 구현체 존재 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(proposalReviewRepository).isNotNull(),
                () -> assertThat(proposalReviewRepository).isInstanceOf(ProposalReviewRepository.class)
        );
    }

    @DisplayName("ProposalReviewRepository 제안서 리뷰 저장 테스트")
    @Test
    void save_test() {
        final ProposalReview savedProposalReview = proposalReviewRepository.save(proposalReview);

        assertThat(savedProposalReview.id()).isEqualTo(proposalReview.id());
    }

    @DisplayName("ProposalReviewRepository 제안서 id에 해당하는 제안서 리뷰 조회 테스트")
    @Test
    void find_by_proposal_id_test() {
        testEntityManager.persist(proposalReview);
        final ProposalReview foundProposalReview = proposalReviewRepository.findByProposalId(proposal.id()).get();

        assertThat(foundProposalReview.id()).isEqualTo(proposalReview.id());
    }

    @DisplayName("ProposalReviewRepository 맵씨 닉네임에 해당하는 제안서 리뷰 최근 3개 조회 테스트")
    @Test
    void find_lately_three_by_nickname_test() {
        testEntityManager.persist(proposalReview);
        IntStream.rangeClosed(1, 3)
                .forEach(i -> {
                    final Proposal proposal = Proposal.builder()
                            .targetMember(targetMember)
                            .mabcci(mabcci)
                            .top("topPictureUrl")
                            .bottom("bottomPictureUrl")
                            .shoes("shoesPictureUrl")
                            .accessory("accessoryPictureUrl")
                            .description("description")
                            .build();
                    final ProposalReview proposalReview =  ProposalReview.builder()
                            .proposal(proposal)
                            .starRating(StarRating.ZERO)
                            .content("내용")
                            .build();
                    testEntityManager.persist(proposal);
                    testEntityManager.persist(proposalReview);
                });

        final List<ProposalReview> proposalReviews = proposalReviewRepository.findLatelyThreeByNickname(mabcci.nickname(), PageRequest.of(0, 3));
        assertThat(proposalReviews.size()).isEqualTo(3);
    }

    @DisplayName("ProposalReviewRepository 맵씨 닉네임에 해당하는 제안서 리스트의 리뷰 조회 테스트")
    @Test
    void find_all_by_mabcci_nickname_test() {
        testEntityManager.persist(proposalReview);

        final List<ProposalReview> proposalReviews = proposalReviewRepository.findAllByMabcciNickname(mabcci.nickname());

        assertThat(proposalReviews).contains(proposalReview);
    }
}
