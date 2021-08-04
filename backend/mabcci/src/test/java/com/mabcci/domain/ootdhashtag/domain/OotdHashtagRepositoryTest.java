package com.mabcci.domain.ootdhashtag.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@EnableJpaAuditing
@DataJpaTest
public class OotdHashtagRepositoryTest {

    @Autowired
    private OotdHashtagRepository ootdHashtagRepository;

    @DisplayName("OotdHashtagRepository 구현체 존재 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdHashtagRepository).isNotNull(),
                () -> assertThat(ootdHashtagRepository).isInstanceOf(OotdHashtagRepository.class)
        );
    }
}
