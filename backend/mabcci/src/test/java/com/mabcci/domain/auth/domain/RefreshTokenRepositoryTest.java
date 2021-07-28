package com.mabcci.domain.auth.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.validation.ConstraintViolationException;

import static com.mabcci.domain.model.EmailTest.EMAIL;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@EnableJpaAuditing
@DataJpaTest
public class RefreshTokenRepositoryTest {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @DisplayName(value = "refresh token 유효성 검증 테스트")
    @ParameterizedTest(name = "{index}. refresh token: {0}")
    @NullAndEmptySource
    public void validateRefreshTokenTest(String value) {
        final RefreshToken refreshToken = RefreshToken.builder()
                .email(EMAIL)
                .refreshToken(value)
                .build();

        assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy(() -> {
            refreshTokenRepository.save(refreshToken);
            testEntityManager.flush();
        });
    }

}
