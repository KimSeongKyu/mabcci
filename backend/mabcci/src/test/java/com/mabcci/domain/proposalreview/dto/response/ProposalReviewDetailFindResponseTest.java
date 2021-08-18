package com.mabcci.domain.proposalreview.dto.response;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.proposal.domain.Proposal;
import com.mabcci.domain.proposalreview.domain.ProposalReview;
import com.mabcci.domain.proposalreview.domain.StarRating;
import com.mabcci.global.common.Email;
import com.mabcci.global.common.Nickname;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.Set;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProposalReviewDetailFindResponseTest {

    private ProposalReviewDetailFindResponse proposalReviewDetailFindResponse;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        now = LocalDateTime.now();
        proposalReviewDetailFindResponse = new ProposalReviewDetailFindResponse(PICTURE, NICKNAME, StarRating.ZERO.ordinal(), "내용", now);
    }

    @DisplayName("ProposalReviewDetailFindResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(proposalReviewDetailFindResponse).isNotNull(),
                () -> assertThat(proposalReviewDetailFindResponse).isExactlyInstanceOf(ProposalReviewDetailFindResponse.class)
        );
    }

    @DisplayName("ProposalReviewDetailFindResponse 인스턴스 스태틱 팩토리 메서드를 이용한 생성 테스트")
    @Test
    void static_factory_constructor_test() {

        final Member targetMember = Member.Builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(Gender.MAN)
                .description(DESCRIPTION)
                .picture(PICTURE)
                .memberRole(MemberRole.USER)
                .build();
        final Member mabcci = Member.Builder()
                .email(Email.of("mabcci@example.com"))
                .password(PASSWORD)
                .nickname(Nickname.of("mabcci"))
                .phone(PHONE)
                .gender(Gender.MAN)
                .description(DESCRIPTION)
                .picture(PICTURE)
                .memberRole(MemberRole.MABCCI)
                .build();
        final Proposal proposal = Proposal.builder()
                .targetMember(targetMember)
                .mabcci(mabcci)
                .top("topPictureUrl")
                .bottom("bottomPictureUrl")
                .shoes("shoesPictureUrl")
                .accessory("accessoryPictureUrl")
                .description("description")
                .build();
        final ProposalReview proposalReview = ProposalReview.builder()
                .proposal(proposal)
                .starRating(StarRating.ZERO)
                .content("내용")
                .build();
        final ProposalReviewDetailFindResponse proposalReviewDetailFindResponse = ProposalReviewDetailFindResponse.ofProposalReview(proposalReview);

        assertAll(
                () -> assertThat(proposalReviewDetailFindResponse).isNotNull(),
                () -> assertThat(proposalReviewDetailFindResponse).isExactlyInstanceOf(ProposalReviewDetailFindResponse.class)
        );
    }

    @DisplayName("ProposalReviewDetailFindResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(proposalReviewDetailFindResponse.memberPicture()).isEqualTo(PICTURE),
                () -> assertThat(proposalReviewDetailFindResponse.nickname()).isEqualTo(NICKNAME),
                () -> assertThat(proposalReviewDetailFindResponse.starRating()).isEqualTo(StarRating.ZERO.ordinal()),
                () -> assertThat(proposalReviewDetailFindResponse.content()).isEqualTo("내용"),
                () -> assertThat(proposalReviewDetailFindResponse.createdDate()).isEqualTo(now)
        );
    }


    @DisplayName("ProposalReviewDetailFindResponse 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final ProposalReviewDetailFindResponse invalidResponse =
                new ProposalReviewDetailFindResponse(null, null, -1, null, LocalDateTime.MAX);

        final Set<ConstraintViolation<ProposalReviewDetailFindResponse>> invalidPropertiesOfValidResponse =
                validator.validate(proposalReviewDetailFindResponse);
        final Set<ConstraintViolation<ProposalReviewDetailFindResponse>> invalidPropertiesOfInvalidResponse =
                validator.validate(invalidResponse);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidResponse.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidResponse.size()).isEqualTo(3)
        );
    }
}
