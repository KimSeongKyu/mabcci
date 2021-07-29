package com.mabcci.domain.auth.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class JwtTokenTypeTest {

    @DisplayName("JwtTokenType 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        Arrays.stream(JwtTokenType.values())
                .forEach(jwtTokenType -> assertAll(
                        () -> assertThat(jwtTokenType).isNotNull(),
                        () -> assertThat(jwtTokenType).isExactlyInstanceOf(JwtTokenType.class)
                ));
    }

    @DisplayName("JwtTokenType 인스턴스 만료 시간 반환 기능 테스트")
    @Test
    void expiration_time_test() {
        final Long accessTokenExpirationTime = 1000 * 60 * 30L;
        final Long refreshTokenExpirationTime = 1000 * 60 * 60 * 24 * 7L;

        assertAll(
                () -> assertThat(JwtTokenType.ACCESS_TOKEN.expirationTime()).isEqualTo(accessTokenExpirationTime),
                () -> assertThat(JwtTokenType.REFRESH_TOKEN.expirationTime()).isEqualTo(refreshTokenExpirationTime)
        );
    }
}
