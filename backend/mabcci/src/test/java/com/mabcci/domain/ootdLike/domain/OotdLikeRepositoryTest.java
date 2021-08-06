package com.mabcci.domain.ootdLike.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@EnableJpaAuditing
@DataJpaTest
class OotdLikeRepositoryTest {

    @Autowired
    private OotdLikeRepository ootdLikeRepository;

    @DisplayName("OotdLikeRepository 구현체 존재 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdLikeRepository).isNotNull(),
                () -> assertThat(ootdLikeRepository).isInstanceOf(OotdLikeRepository.class)
        );
    }

}
