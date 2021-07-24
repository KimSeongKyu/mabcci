package com.mabcci.auth.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class TokenTypeTest {

    public static Stream<Arguments> provideTokenTypesForConstructTest() {
        return Arrays.stream(TokenType.values())
                .map(tokenType -> Arguments.of(tokenType));
    }

    @DisplayName(value = "생성 테스트")
    @ParameterizedTest(name = "{index}. ENUM TYPE : {0}")
    @MethodSource(value = "provideTokenTypesForConstructTest")
    public void constructTest(TokenType tokenType) {
        // then
        assertThat(tokenType).isNotNull();
    }
}
