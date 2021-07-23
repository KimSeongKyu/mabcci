package com.mabcci.auth.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
