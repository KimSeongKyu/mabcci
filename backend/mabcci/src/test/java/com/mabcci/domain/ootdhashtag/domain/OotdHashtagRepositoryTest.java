package com.mabcci.domain.ootdhashtag.domain;

import com.mabcci.domain.hashtag.domain.Hashtag;
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
public class OotdHashtagRepositoryTest {

    @Autowired
    private OotdHashtagRepository ootdHashtagRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Member member;
    private Ootd ootd;
    private Hashtag hashtag;
    private OotdHashtag ootdHashtag;

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
        hashtag = Hashtag.builder()
                .name("해시태그")
                .build();
        ootdHashtag = OotdHashtag.builder()
                .ootd(ootd)
                .hashtag(hashtag)
                .build();

        testEntityManager.persist(member);
        testEntityManager.persist(ootd);
        testEntityManager.persist(hashtag);
    }

    @DisplayName("OotdHashtagRepository 구현체 존재 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdHashtagRepository).isNotNull(),
                () -> assertThat(ootdHashtagRepository).isInstanceOf(OotdHashtagRepository.class)
        );
    }

    @DisplayName("OotdHashtagRepository save 기능 테스트")
    @Test
    void save_test() {
        final OotdHashtag savedOotdHashtag = ootdHashtagRepository.save(ootdHashtag);

        assertThat(savedOotdHashtag.id()).isEqualTo(ootdHashtag.id());
    }

    @DisplayName("OotdHashtagRepository ootd의 hastag들을 조회하는 기능 테스트")
    @Test
    void find_by_ootd_test() {
        testEntityManager.persist(ootdHashtag);

        final List<OotdHashtag> ootdHashtags = ootdHashtagRepository.findByOotd(ootd);

        assertThat(ootdHashtags.size()).isEqualTo(1);
    }
}
