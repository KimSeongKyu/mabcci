package com.mabcci.domain.ootdhashtag.domain;

import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.ootd.domain.Ootd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@EnableJpaAuditing
@DataJpaTest
public class OotdHashtagRepositoryTest {

    @Autowired
    private OotdHashtagRepository ootdHashtagRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private OotdHashtag ootdHashtag;
    private Ootd ootd;
    private Hashtag hashtag;

    @BeforeEach
    void setUp() {
        ootd = Ootd.builder()
                .member(MEMBER)
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
        testEntityManager.persist(ootd);
        testEntityManager.persist(hashtag);

        final OotdHashtag savedOotdHashtag = ootdHashtagRepository.save(ootdHashtag);

        assertThat(savedOotdHashtag.id()).isEqualTo(ootdHashtag.id());
    }
}
