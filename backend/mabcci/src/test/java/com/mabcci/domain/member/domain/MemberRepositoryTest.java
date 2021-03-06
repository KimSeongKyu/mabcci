package com.mabcci.domain.member.domain;

import com.mabcci.domain.category.domain.Category;
import com.mabcci.domain.member.application.MemberFindServiceTest;
import com.mabcci.global.common.Nickname;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static com.mabcci.domain.member.domain.Gender.MAN;
import static com.mabcci.domain.member.domain.MemberRole.USER;
import static com.mabcci.domain.member.domain.MemberSpecsTest.*;
import static com.mabcci.global.common.Email.of;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@EnableJpaAuditing
@DataJpaTest
class MemberRepositoryTest {

    private Member member;
    private MemberSpecs memberSpecs;
    private Category category;

    @BeforeEach
    void setUp() {
        memberSpecs = memberSpecs.Builder()
                .height(HEIGHT)
                .weight(WEIGHT)
                .footSize(FOOT_SIZE)
                .form(BODY_TYPE)
                .build();

        member = Member.Builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(MAN)
                .memberRole(USER)
                .memberSpecs(memberSpecs)
                .build();
        category = new Category(MemberFindServiceTest.CATEGORY_NAME);
    }

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @DisplayName("MemberRepository ????????? ?????? ?????? ?????????")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(memberRepository).isNotNull(),
                () -> assertThat(memberRepository).isInstanceOf(MemberRepository.class)
        );
    }

    @DisplayName("MemberRepository save ?????? ?????????")
    @Test
    void save_test() {
        final Member savedMember = memberRepository.save(member);
        assertThat(savedMember.id()).isEqualTo(member.id());
    }

    @DisplayName("MemberRepository findByNickname ?????? ?????????")
    @Test
    void find_by_nickname_test() {
        testEntityManager.persist(member);
        final Member findMember = memberRepository.findByNickName(member.nickname()).get();

        assertThat(findMember.id()).isEqualTo(member.id());

    }

    @DisplayName("MemberRepository findByNicknameContains ?????? ?????????")
    @Test
    void find_by_nickname_contains_test() {
        testEntityManager.persist(member);
        final List<Member> foundMembers = memberRepository.findByNicknameContains(NICKNAME.nickname());

        assertThat(foundMembers.size()).isEqualTo(1);
    }

    @DisplayName("MemberRepository findByNickname ?????? ?????? ?????????")
    @Test
    void find_by_nickname_fail_test() {
        final Nickname nickname = Nickname.of("invalidNickName");

        testEntityManager.persist(member);
        final Optional<Member> findMember = memberRepository.findByNickName(nickname);

        assertThat(findMember.isPresent()).isFalse();
    }

    @DisplayName(value = "MemberRepository findByEmailAndPassword ?????? ?????????")
    @Test
    public void find_by_email_and_password_test() {
        testEntityManager.persist(member);

        final Member findMember = memberRepository.findByEmailAndPassword(EMAIL, PASSWORD).get();

        assertThat(findMember.id()).isEqualTo(member.id());
    }

    @DisplayName(value = "MemberRepository findByEmailAndPassword ?????? ?????? ?????????")
    @Test
    public void find_by_email_and_password_fail_test() {
        testEntityManager.persist(member);
        final Optional<Member> findMember = memberRepository.findByEmailAndPassword(of(EMPTY), PASSWORD);

        assertThat(findMember.isPresent()).isFalse();
    }

    @DisplayName("MemberRepository findAll ?????? ?????????")
    @Test
    void find_all_test() {
        testEntityManager.persist(member);
        final List<Member> members = memberRepository.findAll();

        assertAll(
                () -> assertThat(members).isNotNull(),
                () -> assertThat(members.size()).isEqualTo(1)
        );
    }

    @DisplayName("MemberRepository findAllByIsPopularMabcci ?????? ?????????")
    @Test
    void find_all_by_is_popular_mabcci_test() {
        ReflectionTestUtils.setField(member, "isPopularMabcci", true);
        testEntityManager.persist(member);
        final List<Member> members = memberRepository.findAllByIsPopularMabcci();

        assertAll(
                () -> assertThat(members).isNotNull(),
                () -> assertThat(members.size()).isEqualTo(1)
        );
    }

    @DisplayName("MemberRepository delete ?????? ?????????")
    @Test
    void delete_test() {
        final Member savedMember = testEntityManager.persist(member);

        memberRepository.delete(savedMember);
        final Optional<Member> findMember = memberRepository.findById(savedMember.id());

        assertThat(findMember.isPresent()).isFalse();
    }

}