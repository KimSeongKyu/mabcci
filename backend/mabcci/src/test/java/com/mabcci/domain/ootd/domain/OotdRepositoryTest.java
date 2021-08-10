package com.mabcci.domain.ootd.domain;

import com.mabcci.domain.follow.domain.Follow;
import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.ootd.dto.response.OotdResponse;
import com.mabcci.domain.ootdhashtag.domain.OotdHashtag;
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

import java.nio.file.Path;
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

    private Ootd ootd;
    private Ootd firstFollowingMemberOotd;
    private Ootd secondFollowingMemberOotd;
    private Member member;
    private Member firstFollowingMember;
    private Member secondFollowingMember;
    private Follow firstFollow;
    private Follow secondFollow;

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

    @DisplayName("OotdRepository 전체 ootd 리스트 조회 테스트")
    @Test
    void find_all_test() {
        testEntityManager.persist(member);
        for (int i = 0; i < 21; i++) {
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

        Page<Ootd> ootdsOfFollowingMember = ootdRepository.findAllOfFollowing(member, PageRequest.of(0, 20));

        assertThat(ootdsOfFollowingMember.toList().size()).isEqualTo(2);
    }

    @DisplayName("OotdRepository 전체 ootd 리스트의 ootd id, 닉네임, 대표 사진, 해시태그들 조회")
    @Test
    void find_all_with_id_and_nickname_and_picture_and_hashtags_test() {
        final OotdPicture firstOotdPicture = OotdPicture.builder()
                .ootd(ootd)
                .url("testUrl1")
                .fileName("testFilePath1")
                .build();
        final OotdPicture secondOotdPicture = OotdPicture.builder()
                .ootd(ootd)
                .url("testUrl2")
                .fileName("testFilePath2")
                .build();
        final Hashtag firstHashtag = Hashtag.builder()
                .name("해시태그1")
                .build();
        final Hashtag secondHashtag = Hashtag.builder()
                .name("해시태그2")
                .build();
        final OotdHashtag firstOotdHashtag = OotdHashtag.builder()
                .ootd(ootd)
                .hashtag(firstHashtag)
                .build();
        final OotdHashtag secondOotdHashtag = OotdHashtag.builder()
                .ootd(ootd)
                .hashtag(secondHashtag)
                .build();

        testEntityManager.persist(member);
        testEntityManager.persist(ootd);
        testEntityManager.persist(firstOotdPicture);
        testEntityManager.persist(secondOotdPicture);
        testEntityManager.persist(firstHashtag);
        testEntityManager.persist(secondHashtag);
        testEntityManager.persist(firstOotdHashtag);
        testEntityManager.persist(secondOotdHashtag);

        final List<OotdResponse> ootdResponses = ootdRepository.findAllWithIdAndNicknameAndPictureAndHashtags(PageRequest.of(0, 20));
        final OotdResponse ootdResponse = ootdResponses.get(0);

        assertAll(
                () -> assertThat(ootdResponses.size()).isEqualTo(1),
                () -> assertThat(ootdResponse.getId()).isEqualTo(ootd.id()),
                () -> assertThat(ootdResponse.getNickname()).isEqualTo(member.nickname().nickname()),
                () -> assertThat(ootdResponse.getPicture()).isEqualTo(Path.of(firstOotdPicture.url(), "/", firstOotdPicture.fileName())),
                () -> assertThat(ootdResponse.getHashtags().size()).isEqualTo(2)
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
