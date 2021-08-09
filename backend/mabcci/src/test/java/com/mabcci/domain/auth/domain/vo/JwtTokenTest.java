package com.mabcci.domain.auth.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class JwtTokenTest {

    @DisplayName("JwtToken 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        final String value = "test.jwt.token";
        final JwtToken jwtToken = JwtToken.of(value);

        assertAll(
                () -> assertThat(jwtToken).isNotNull(),
                () -> assertThat(jwtToken).isExactlyInstanceOf(JwtToken.class)
        );
    }

    @DisplayName("JwtToken 인스턴스 디폴트 생성자를 이용한 생성 여부 테스트")
    @Test
    void default_constructor_test() {
        final JwtToken jwtToken = new JwtToken();

        assertAll(
                () -> assertThat(jwtToken).isNotNull(),
                () -> assertThat(jwtToken).isExactlyInstanceOf(JwtToken.class)
        );
    }

    @DisplayName("JwtToken 인스턴스 문자열 값 반환 기능 테스트")
    @Test
    void jwtToken_test() {
        final String value = "test.jwt.token";
        final JwtToken jwtToken = JwtToken.of(value);

        assertThat(jwtToken.jwtToken()).isEqualTo(value);
    }

    @DisplayName("JwtToken 인스턴스 equals() & hashCode() 기능 테스트")
    @Test
    void equals_and_hashCode_test() {
        final String value = "test.jwt.token";
        final JwtToken jwtToken = JwtToken.of(value);
        final JwtToken other = JwtToken.of(value);

        assertAll(
                () -> assertThat(jwtToken).isEqualTo(other),
                () -> assertThat(jwtToken.hashCode()).isEqualTo(other.hashCode())
        );
    }
}
