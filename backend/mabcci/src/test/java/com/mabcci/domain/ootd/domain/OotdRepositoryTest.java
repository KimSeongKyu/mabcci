package com.mabcci.domain.ootd.domain;

import com.mabcci.domain.follow.domain.Follow;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.ootdpicture.domain.OotdPicture;
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

    @Autowired private OotdRepository ootdRepository;
    @Autowired private TestEntityManager testEntityManager;

    private Member member;
    private Member firstFollowingMember;
    private Member secondFollowingMember;
    private Follow firstFollow;
    private Follow secondFollow;
    private Ootd ootd;
    private Ootd firstFollowingMemberOotd;
    private Ootd secondFollowingMemberOotd;
    private OotdPicture firstFollowingMemberOotdPicture;
    private OotdPicture secondFollowingMemberOotdPicture;

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
        firstFollow = Follow.Builder()
                .follower(member)
                .following(firstFollowingMember)
                .build();
        secondFollow = Follow.Builder()
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
        firstFollowingMemberOotdPicture = OotdPicture.builder()
                .url("firstTestUrl")
                .fileName("firstTestFileName")
                .ootd(firstFollowingMemberOotd)
                .build();
        secondFollowingMemberOotdPicture = OotdPicture.builder()
                .url("secondTestUrl")
                .fileName("secondTestFileName")
                .ootd(secondFollowingMemberOotd)
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

    @DisplayName("OotdRepository ootd 저장 테스트")
    @Test
    void save_test() {
        testEntityManager.persist(member);

        final Ootd savedOotd = ootdRepository.save(ootd);

        assertThat(savedOotd.id()).isEqualTo(ootd.id());
    }


    @DisplayName("OotdRepository ootd id를 이용한 조회 테스트")
    @Test
    void find_by_id_test() {
        testEntityManager.persist(member);
        testEntityManager.persist(ootd);

        final Ootd foundOotd = ootdRepository.findById(ootd.id()).get();

        assertThat(foundOotd.id()).isEqualTo(ootd.id());
    }

    @DisplayName("OotdRepository ootd 상세 조회 테스트")
    @Test
    void find_ootd_detail_by_id_test() {
        testEntityManager.persist(firstFollowingMember);
        testEntityManager.persist(firstFollowingMemberOotd);
        testEntityManager.persist(firstFollowingMemberOotdPicture);

        final Ootd foundOotd = ootdRepository.findOotdDetailById(firstFollowingMemberOotd.id()).get();

        assertThat(foundOotd.id()).isEqualTo(firstFollowingMemberOotd.id());
    }

    @DisplayName("OotdRepository 팔로잉한 멤버의 ootd 리스트 조회 테스트")
    @Test
    void find_all_of_following_test() {
        testEntityManager.persist(member);
        testEntityManager.persist(firstFollowingMember);
        testEntityManager.persist(secondFollowingMember);
        testEntityManager.persist(firstFollow);
        testEntityManager.persist(secondFollow);
        testEntityManager.persist(firstFollowingMemberOotd);
        testEntityManager.persist(secondFollowingMemberOotd);
        testEntityManager.persist(firstFollowingMemberOotdPicture);
        testEntityManager.persist(secondFollowingMemberOotdPicture);

        final Page<Ootd> ootdsOfFollowingMember = ootdRepository.findOotdsOfFollowing(member, PageRequest.of(0, 20));

        assertThat(ootdsOfFollowingMember.toList().size()).isEqualTo(2);
    }

    @DisplayName("OotdRepository 전체 ootd 리스트 조회 테스트")
    @Test
    void find_ootds_test() {
        testEntityManager.persist(member);
        for (int i = 0; i < 21; i++) {
            final Ootd ootd = Ootd.builder()
                    .member(member)
                    .content("content")
                    .top("top")
                    .bottom("bottom")
                    .shoes("shoes")
                    .accessory("accessory")
                    .build();
            testEntityManager.persist(ootd);
            testEntityManager.persist(OotdPicture.builder()
                    .ootd(ootd)
                    .url("testUrl")
                    .fileName("testFileName")
                    .build());
        }

        final Page<Ootd> firstPageOotds = ootdRepository.findOotds(PageRequest.of(0, 20));
        final Page<Ootd> secondPageOotds = ootdRepository.findOotds(PageRequest.of(1, 20));

        assertAll(
                () -> assertThat(firstPageOotds.getSize()).isEqualTo(20),
                () -> assertThat(firstPageOotds.toList().size()).isEqualTo(20),
                () -> assertThat(secondPageOotds.toList().size()).isEqualTo(1),
                () -> assertThat(firstPageOotds.getTotalPages()).isEqualTo(2)
        );
    }

    @DisplayName("OotdRepository ootd 삭제 테스트")
    @Test
    void delete_test() {
        testEntityManager.persist(member);
        testEntityManager.persist(ootd);

        assertThat(ootdRepository.existsById(ootd.id())).isTrue();

        ootdRepository.delete(ootd);

        assertThat(ootdRepository.existsById(ootd.id())).isFalse();
    }
}
