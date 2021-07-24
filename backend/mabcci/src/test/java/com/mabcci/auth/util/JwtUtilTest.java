package com.mabcci.auth.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class JwtUtilTest {

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
    @Test
    public void createPayloadTest() {
        // given
        JwtUtil jwtUtil = new JwtUtil();
        String[] expectedKeys = new String[]{"iss", "sub", "aud", "exp", "nbf", "iat", "nickName"};

        // when
        Map<String, Object> payload = jwtUtil.createPayload("닉네임");

        // then
        assertThat(payload.keySet()).contains(expectedKeys);
    }

    @DisplayName(value = "Token 생성 테스트")
    @Test
    public void createTokenTest() {
        // given
        JwtUtil jwtUtil = new JwtUtil();

        // when
        String token = jwtUtil.createToken("닉네임");

        // then
        Arrays.stream(token.split("."))
                .forEach(tokenSplitByComma -> assertThat(tokenSplitByComma).isBase64());
    }
}
