package com.mabcci.domain.ootd.domain;

import com.mabcci.domain.follow.domain.Follow;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.global.common.Email;
import com.mabcci.global.common.Nickname;
import com.mabcci.global.common.Phone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

import static com.mabcci.domain.member.domain.Gender.MAN;
import static com.mabcci.domain.member.domain.MemberRole.USER;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@EnableJpaAuditing
@DataJpaTest
class OotdRepositoryTest {

    private Ootd ootd;
    private Ootd firstFollowingMemberOotd;
    private Ootd secondFollowingMemberOotd;
    private Member member;
    private Member firstFollowingMember;
    private Member secondFollowingMember;
    private Follow follow;

    @Autowired
    private OotdRepository ootdRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        member = Member.Builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(MAN)
                .memberRole(USER)
                .build();
        firstFollowingMember = Member.Builder()
                .email(Email.of("test1@example.com"))
                .password(PASSWORD)
                .nickname(Nickname.of("닉네임1"))
                .phone(Phone.of("010-2349-1823"))
                .gender(MAN)
                .memberRole(USER)
                .build();
        secondFollowingMember = Member.Builder()
                .email(Email.of("test2@example.com"))
                .password(PASSWORD)
                .nickname(Nickname.of("닉네임2"))
                .phone(Phone.of("010-2349-1833"))
                .gender(MAN)
                .memberRole(USER)
                .build();
        follow = Follow.Builder()
                .follower(member)
                .following(firstFollowingMember)
                .build();
        follow = Follow.Builder()
                .follower(member)
                .following(secondFollowingMember)
                .build();

        ootd = Ootd.builder()
                .member(member)
                .content("content")
                .top("top")
                .bottom("bottom")
                .shoes("shoes")
                .accessory("accessory")
                .build();
        firstFollowingMemberOotd = Ootd.builder()
                .member(firstFollowingMember)
                .content("content")
                .top("top")
                .bottom("bottom")
                .shoes("shoes")
                .accessory("accessory")
                .build();
        secondFollowingMemberOotd = Ootd.builder()
                .member(secondFollowingMember)
                .content("content")
                .top("top")
                .bottom("bottom")
                .shoes("shoes")
                .accessory("accessory")
                .build();
    }

    @DisplayName("OotdRepository 구현체 존재 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdRepository).isNotNull(),
                () -> assertThat(ootdRepository).isInstanceOf(OotdRepository.class)
        );
    }

    @DisplayName("OotdRepository save 기능 테스트")
    @Test
    void save_test() {
        testEntityManager.persist(member);

        final Ootd savedOotd = ootdRepository.save(ootd);

        assertThat(savedOotd.id()).isEqualTo(ootd.id());
    }


    @DisplayName("OotdRepository findById 기능 테스트")
    @Test
    void find_by_id_test() {
        testEntityManager.persist(member);
        testEntityManager.persist(ootd);

        final Ootd foundOotd = ootdRepository.findById(ootd.id()).get();

        assertThat(foundOotd.id()).isEqualTo(ootd.id());
    }

    @DisplayName("OotdRepository 전체 ootd 리스트 조회 기능 테스트")
    @Test
    void find_all_test() {
        testEntityManager.persist(member);
        for(int i = 0; i < 21; i++) {
            testEntityManager.persist(Ootd.builder()
                    .member(member)
                    .content("content")
                    .top("top")
                    .bottom("bottom")
                    .shoes("shoes")
                    .accessory("accessory")
                    .build());
        }

        Page<Ootd> firstPageOotds = ootdRepository.findAll(PageRequest.of(0, 20));
        Page<Ootd> secondPageOotds = ootdRepository.findAll(PageRequest.of(1, 20));

        assertAll(
                () -> assertThat(firstPageOotds.getSize()).isEqualTo(20),
                () -> assertThat(firstPageOotds.toList().size()).isEqualTo(20),
                () -> assertThat(secondPageOotds.toList().size()).isEqualTo(1),
                () -> assertThat(firstPageOotds.getTotalPages()).isEqualTo(2)
        );
    }

    @DisplayName("OotdRepository 팔로잉한 멤버의 ootd 리스트 조회 기능 테스트")
    @Test
    void find_all_of_following_test() {
        testEntityManager.persist(member);
        testEntityManager.persist(firstFollowingMember);
        testEntityManager.persist(secondFollowingMember);
        testEntityManager.persist(follow);
        testEntityManager.persist(firstFollowingMemberOotd);
        testEntityManager.persist(secondFollowingMemberOotd);

        Page<Ootd> ootdsOfFollowingMember = ootdRepository.findAllOfFollowing(member, PageRequest.of(0, 20));

        assertThat(ootdsOfFollowingMember.toList().size()).isEqualTo(2);
    }
}
