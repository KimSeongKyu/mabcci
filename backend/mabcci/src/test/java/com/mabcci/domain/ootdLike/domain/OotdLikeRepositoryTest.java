package com.mabcci.domain.ootdLike.domain;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.domain.Ootd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

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
class OotdLikeRepositoryTest {

    @Autowired private OotdLikeRepository ootdLikeRepository;
    @Autowired private TestEntityManager testEntityManager;

    private Member member;
    private Ootd ootd;
    private OotdLike ootdLike;

    @BeforeEach
    void setUp() {
        member = Member.Builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(Gender.MAN)
                .description(DESCRIPTION)
                .picture(PICTURE)
                .memberRole(MemberRole.USER)
                .build();
        ootd = Ootd.builder()
                .member(member)
                .content("content")
                .top("top")
                .bottom("bottom")
                .shoes("shoes")
                .accessory("accessory")
                .views(0L)
                .build();
        ootdLike = OotdLike.builder()
                .member(member)
                .ootd(ootd)
                .build();
        testEntityManager.persist(member);
        testEntityManager.persist(ootd);
        testEntityManager.persist(ootdLike);
    }

    @DisplayName("OotdLikeRepository 구현체 존재 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdLikeRepository).isNotNull(),
                () -> assertThat(ootdLikeRepository).isInstanceOf(OotdLikeRepository.class)
        );
    }

    @DisplayName("OotdLikeRepository ootd 좋아요 생성 테스트")
    @Test
    void save_test() {
        final OotdLike savedOotdLike = ootdLikeRepository.save(ootdLike);

        assertThat(savedOotdLike.id()).isEqualTo(ootdLike.id());
    }

    @DisplayName("OotdLikeRepository ootd와 member로 ootd 좋아요 조회 테스트")
    @Test
    void find_by_ootd_and_member_test() {
        final OotdLike foundOotdLike = ootdLikeRepository.findByOotdAndNickname(ootd.id(), NICKNAME).get();

        assertThat(foundOotdLike.id()).isEqualTo(ootdLike.id());
    }
}
