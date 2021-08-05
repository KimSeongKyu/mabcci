package com.mabcci.domain.follow.domain;

import com.mabcci.domain.member.domain.*;
import com.mabcci.global.common.Email;
import com.mabcci.global.common.Nickname;
import com.mabcci.global.common.Password;
import com.mabcci.global.common.Phone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.Optional;

import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@EnableJpaAuditing
@DataJpaTest
class FollowRepositoryTest {

    @Autowired private TestEntityManager testEntityManager;
    @Autowired private FollowRepository followRepository;
    @Autowired private MemberRepository memberRepository;

    private Member following;
    private Member follower;

    @BeforeEach
    void setUp() {
         following = member(Email.of("following@email.com"), PASSWORD, Nickname.of("following"), Phone.of("010-1234-5678"));
         follower = member(Email.of("follower@email.com"), PASSWORD, Nickname.of("follower"), Phone.of("010-5678-1234"));
    }

    @DisplayName("FollowRepository 인스턴스 save() 기능 테스트")
    @Test
    void save_test() {
        final Follow follow = follow(following, follower);
        final Follow savedFollow = followRepository.save(follow);

        assertThat(follow).isEqualTo(savedFollow);
    }

    @DisplayName("FollowRepository 인스턴스 save() 기능 테스트")
    @Test
    void find_test() {
        final Follow follow = testEntityManager.persist(follow(following, follower));
        final Follow findFollow = followRepository.findById(follow.id()).get();

        assertThat(follow).isEqualTo(findFollow);
    }

    @DisplayName("FollowRepository 인스턴스 save() 기능 테스트")
    @Test
    void findAll_test() {
        memberRepository.save(following);
        memberRepository.save(follower);
        final Follow follow = testEntityManager.persist(follow(following, follower));
        final List<Follow> follows = followRepository.findAll();

        assertAll(
                () -> assertThat(follows.size()).isEqualTo(1),
                () -> assertThat(follows.get(0)).isEqualTo(follow)
        );
    }

    @DisplayName("FollowRepository 인스턴스 save() 기능 테스트")
    @Test
    void delete_test() {
        final Follow follow = testEntityManager.persist(follow(following, follower));
        followRepository.delete(follow);

        final Optional<Follow> findFollow = followRepository.findById(1L);
        assertThat(findFollow.isPresent()).isFalse();
    }

    private static Member member(Email email, Password password, Nickname nickname, Phone phone) {
        return Member.Builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .phone(phone)
                .gender(Gender.MALE)
                .role(MemberRole.USER)
                .build();
    }

    private static Follow follow(Member following, Member follower) {
        return Follow.Builder()
                .following(following)
                .follower(follower)
                .build();
    }

}