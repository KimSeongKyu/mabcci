package com.mabcci.domain.proposal.domain;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.global.common.Email;
import com.mabcci.global.common.Nickname;
import com.mabcci.global.common.Phone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

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
class ProposalRepositoryTest {

    @Autowired private ProposalRepository proposalRepository;
    @Autowired private TestEntityManager testEntityManager;

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
        testEntityManager.persist(targetMember);
        testEntityManager.persist(mabcci);
    }

    @DisplayName("ProposalRepository 구현체 존재 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(proposalRepository).isNotNull(),
                () -> assertThat(proposalRepository).isInstanceOf(ProposalRepository.class)
        );
    }

    @DisplayName("ProposalRepository 제안서 저장 테스트")
    @Test
    void save_test() {
        final Proposal savedProposal = proposalRepository.save(proposal);

        assertThat(savedProposal.id()).isEqualTo(proposal.id());
    }

    @DisplayName("ProposalRepository 내가 제안한 제안서 리스트 조회 테스트")
    @Test
    void find_all_by_mabcci_test() {
        testEntityManager.persist(proposal);
        final List<Proposal> proposals = proposalRepository.findAllByMabcciNickname(mabcciNickname);

        assertThat(proposals).contains(proposal);
    }
}
