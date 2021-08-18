package com.mabcci.domain.proposal.dto.response;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.proposal.domain.Proposal;
import com.mabcci.global.common.Email;
import com.mabcci.global.common.Nickname;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.domain.picture.common.PictureUtilTest.PICTURE_FILES;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProposalDetailFindResponseTest {

    private ProposalDetailFindResponse proposalDetailFindResponse;
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
        proposalDetailFindResponse =
                new ProposalDetailFindResponse(proposal.targetMember().nickname(), proposal.mabcci().nickname(),
                        proposal.top(), proposal.bottom(), proposal.shoes(), proposal.accessory(), proposal.description());
    }

    @DisplayName("ProposalDetailFindResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(proposalDetailFindResponse).isNotNull(),
                () -> assertThat(proposalDetailFindResponse).isExactlyInstanceOf(ProposalDetailFindResponse.class)
        );
    }

    @DisplayName("ProposalDetailFindResponse 인스턴스 스태틱 팩토리 메서드를 이용한 생성 테스트")
    @Test
    void static_factory_constructor_test() {
        final ProposalDetailFindResponse proposalDetailFindResponse = ProposalDetailFindResponse.ofProposal(proposal);

        assertAll(
                () -> assertThat(proposalDetailFindResponse).isNotNull(),
                () -> assertThat(proposalDetailFindResponse).isExactlyInstanceOf(ProposalDetailFindResponse.class)
        );
    }

    @DisplayName("ProposalDetailFindResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
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
