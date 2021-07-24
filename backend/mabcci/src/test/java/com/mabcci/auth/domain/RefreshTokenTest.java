package com.mabcci.auth.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RefreshTokenTest {

    @DisplayName(value = "생성 테스트")
    @Test
    public void constructTest() {
        // given
        String email = "example@example.com";
        String value = "test.refresh.token";

        // when
        RefreshToken refreshToken = RefreshToken.builder()
                .email(email)
                .refreshToken(value)
                .build();

        // then
        assertThat(refreshToken).isNotNull();
    }
}
