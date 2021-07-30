package com.mabcci.domain.auth.domain;

import com.mabcci.domain.auth.domain.vo.JwtToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.domain.model.EmailTest.EMAIL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class RefreshTokenTest {

    @DisplayName("RefreshToken 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        final JwtToken value = JwtToken.of("test.refresh.token");
        final RefreshToken refreshToken = RefreshToken.builder()
                .email(EMAIL)
                .refreshToken(value)
                .build();

        assertAll(
                () -> assertThat(refreshToken).isNotNull(),
                () -> assertThat(refreshToken).isExactlyInstanceOf(RefreshToken.class)
        );
    }
}
