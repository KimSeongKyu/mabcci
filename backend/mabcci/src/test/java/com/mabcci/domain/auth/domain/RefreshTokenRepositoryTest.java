package com.mabcci.domain.auth.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.mabcci.global.common.EmailTest.EMAIL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@EnableJpaAuditing
@DataJpaTest
class RefreshTokenRepositoryTest {

    @Autowired private RefreshTokenRepository refreshTokenRepository;
    @Autowired private TestEntityManager testEntityManager;

    private RefreshToken refreshToken;

    @BeforeEach
    void setUp() {
        refreshToken = RefreshToken.builder()
                .email(EMAIL)
                .refreshToken("test.refresh.token")
                .build();
    }

    @DisplayName("RefreshTokenRepository 구현체 존재 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(refreshTokenRepository).isNotNull(),
                () -> assertThat(refreshTokenRepository).isInstanceOf(RefreshTokenRepository.class)
        );
    }

    @DisplayName("RefreshTokenRepository RefreshToken 저장 테스트")
    @Test
    void save_test() {
        final RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);
        assertThat(savedRefreshToken.email()).isEqualTo(refreshToken.email());
    }

    @DisplayName("RefreshTokenRepository RefreshToken 삭제 테스트")
    @Test
    void delete_test() {
        testEntityManager.persist(refreshToken);

        assertThat(refreshTokenRepository.existsById(EMAIL)).isTrue();

        refreshTokenRepository.delete(refreshToken);

        assertThat(refreshTokenRepository.existsById(EMAIL)).isFalse();
    }
}
