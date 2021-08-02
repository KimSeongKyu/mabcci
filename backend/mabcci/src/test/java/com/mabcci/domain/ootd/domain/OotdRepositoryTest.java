package com.mabcci.domain.ootd.domain;

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
class OotdRepositoryTest {

    private Ootd ootd;

    @Autowired
    private OotdRepository ootdRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        ootd = Ootd.builder()
                .member(MEMBER)
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
        testEntityManager.persist(MEMBER);

        final Ootd savedOotd = ootdRepository.save(ootd);

        assertThat(savedOotd.id()).isEqualTo(ootd.id());
    }
}
