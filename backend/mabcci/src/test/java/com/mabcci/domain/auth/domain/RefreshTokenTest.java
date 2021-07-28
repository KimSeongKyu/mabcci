package com.mabcci.domain.auth.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.domain.model.EmailTest.EMAIL;
import static org.assertj.core.api.Assertions.assertThat;

public class RefreshTokenTest {

    @DisplayName(value = "생성 테스트")
    @Test
    public void constructTest() {
        final String value = "test.refresh.token";
        final RefreshToken refreshToken = RefreshToken.builder()
                .email(EMAIL)
                .refreshToken(value)
                .build();

        assertThat(refreshToken).isNotNull();
    }
}
