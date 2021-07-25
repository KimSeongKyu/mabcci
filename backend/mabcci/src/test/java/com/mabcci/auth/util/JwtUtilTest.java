package com.mabcci.auth.util;

import com.mabcci.auth.domain.TokenType;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.security.Key;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class JwtUtilTest {

    public static Stream<Arguments> provideTokenTypesForTestsAboutToken() {
        return Arrays.stream(TokenType.values())
                .map(tokenType -> Arguments.of(tokenType));
    }

    @DisplayName(value = "생성 테스트")
    @Test
    public void constructTest() {
        // given and when
        JwtUtil jwtUtil = new JwtUtil();

        // then
        assertThat(jwtUtil).isNotNull();
    }

    @DisplayName(value = "Header 생성 테스트")
    @Test
    public void createHeaderTest() {
        // given
        JwtUtil jwtUtil = new JwtUtil();
        String[] expectedKeys = new String[]{"typ", "alg"};

        // when
        Map<String, Object> header = jwtUtil.createHeader();

        // then
        assertThat(header.keySet()).containsExactly(expectedKeys);
    }

    @DisplayName(value = "Payload 생성 테스트")
    @ParameterizedTest(name = "{index}. Token Type: {0}")
    @MethodSource(value = "provideTokenTypesForTestsAboutToken")
    public void createPayloadTest(TokenType tokenType) {
        // given
        JwtUtil jwtUtil = new JwtUtil();
        String[] expectedKeys = new String[]{"iss", "sub", "aud", "exp", "nbf", "iat", "email"};

        // when
        Map<String, Object> payload = jwtUtil.createPayload(tokenType, "example@example.com");

        // then
        assertThat(payload.keySet()).contains(expectedKeys);
    }

    @DisplayName(value = "Secret key 생성 테스트")
    @Test
    public void createSecretKeyTest() {
        // given
        JwtUtil jwtUtil = new JwtUtil();

        // when
        Key key = jwtUtil.createSecretKey();

        // then
        assertThat(key.getAlgorithm()).isEqualTo(SignatureAlgorithm.HS256.getJcaName());
    }

    @DisplayName(value = "Token 생성 테스트")
    @ParameterizedTest(name = "{index}. Token Type: {0}")
    @MethodSource(value = "provideTokenTypesForTestsAboutToken")
    public void createTokenTest(TokenType tokenType) {
        // given
        JwtUtil jwtUtil = new JwtUtil();

        // when
        String token = jwtUtil.createToken(tokenType, "example@example.com");

        // then
        Arrays.stream(token.split("."))
                .forEach(tokenSplitByComma -> assertThat(tokenSplitByComma).isBase64());
    }

    @DisplayName(value = "유효한 토큰 검증 테스트")
    @ParameterizedTest(name = "{index}. Token Type: {0}")
    @MethodSource(value = "provideTokenTypesForTestsAboutToken")
    public void isValidTokenTest(TokenType tokenType) {
        // given
        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.createToken(tokenType, "example@example.com");

        // when
        boolean validity = jwtUtil.isValidToken(token);

        // then
        assertThat(validity).isTrue();
    }

    @DisplayName(value = "유효하지 않은 토큰 검증 테스트")
    @ParameterizedTest(name = "{index}. Token Type: {0}")
    @MethodSource(value = "provideTokenTypesForTestsAboutToken")
    public void isNotValidTokenTest(TokenType tokenType) {
        // given
        JwtUtil jwtUtil = new JwtUtil();
        String token = "invalid.test.token";

        // when
        boolean validity = jwtUtil.isValidToken(token);

        // then
        assertThat(validity).isFalse();
    }
}
