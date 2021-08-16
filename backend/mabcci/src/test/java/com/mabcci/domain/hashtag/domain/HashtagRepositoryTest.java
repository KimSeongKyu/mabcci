package com.mabcci.domain.hashtag.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@EnableJpaAuditing
@DataJpaTest
class HashtagRepositoryTest {

    @Autowired private HashtagRepository hashtagRepository;
    @Autowired private TestEntityManager testEntityManager;

    private Hashtag hashtag;

    @BeforeEach
    void setUp() {
        hashtag = Hashtag.builder()
                .name("해시태그")
                .build();
    }

    @DisplayName("HashtagRepository 구현체 존재 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(hashtagRepository).isNotNull(),
                () -> assertThat(hashtagRepository).isInstanceOf(HashtagRepository.class)
        );
    }

    @DisplayName("HashtagRepository save 기능 테스트")
    @Test
    void save_test() {
        final Hashtag hashtag = Hashtag.builder()
                .name("해시태그")
                .build();
        final Hashtag savedHashtag = hashtagRepository.save(hashtag);

        assertThat(savedHashtag.id()).isEqualTo(hashtag.id());
    }

    @DisplayName("HashtagRepository findByName 기능 테스트")
    @Test
    void find_by_name_test() {
        testEntityManager.persist(hashtag);
        final Optional<Hashtag> foundHashtag = hashtagRepository.findByName(hashtag.name());

        assertAll(
                () -> assertThat(foundHashtag).isNotEmpty(),
                () -> assertThat(foundHashtag.get().id()).isEqualTo(hashtag.id())
        );
    }

    @DisplayName("HashtagRepository findByNameContains 기능 테스트")
    @Test
    void find_by_name_contains_test() {
        testEntityManager.persist(hashtag);
        final List<Hashtag> foundHashtags = hashtagRepository.findByNameContains(hashtag.name());

        assertThat(foundHashtags.size()).isEqualTo(1);
    }
}
