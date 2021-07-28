package com.mabcci.domain.auth.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class JwtTokenTest {

    public static final JwtToken JWT_TOKEN = JwtToken.of("test.jwt.token");

    @DisplayName(value = "JwtToken 인스턴스 생성 여부 테스트")
    @Test
    public void constructor_test() {
        final String value = "test.jwt.token";
        final JwtToken jwtToken = JwtToken.of(value);

        assertAll(
                () -> assertThat(jwtToken).isNotNull(),
                () -> assertThat(jwtToken).isExactlyInstanceOf(JwtToken.class)
        );
    }

    @DisplayName(value = "JwtToken 인스턴스 디폴트 생성자를 이용한 생성 여부 테스트")
    @Test
    public void default_constructor_test() {
        final JwtToken jwtToken = new JwtToken();

        assertAll(
                () -> assertThat(jwtToken).isNotNull(),
                () -> assertThat(jwtToken).isExactlyInstanceOf(JwtToken.class)
        );
    }

    @DisplayName(value = "JwtToken 인스턴스 jwtToken() 기능 테스트")
    @Test
    public void jwtToken_test() {
        final String value = "test.jwt.token";
        final JwtToken jwtToken = JwtToken.of(value);

        assertThat(jwtToken.jwtToken()).isEqualTo(value);
    }
}
