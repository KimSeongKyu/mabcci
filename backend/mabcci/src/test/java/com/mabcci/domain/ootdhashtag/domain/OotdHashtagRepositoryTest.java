package com.mabcci.domain.ootdhashtag.domain;

import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.ootd.domain.Ootd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.mabcci.domain.member.domain.Gender.MALE;
import static com.mabcci.domain.member.domain.MemberRole.USER;
import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static com.mabcci.domain.model.EmailTest.EMAIL;
import static com.mabcci.domain.model.NicknameTest.NICKNAME;
import static com.mabcci.domain.model.PasswordTest.PASSWORD;
import static com.mabcci.domain.model.PhoneTest.PHONE;
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
        member = Member.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(MALE)
                .role(USER)
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
        testEntityManager.persist(member);
        testEntityManager.persist(ootd);
        testEntityManager.persist(hashtag);

        final OotdHashtag savedOotdHashtag = ootdHashtagRepository.save(ootdHashtag);

        assertThat(savedOotdHashtag.id()).isEqualTo(ootdHashtag.id());
    }
}
