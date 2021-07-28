package com.mabcci.domain.auth.domain;

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

    public static Stream<Arguments> provideTokenTypesAndExpirationTimesForGetExpirationTimeTest() {
        return Stream.of(
                Arguments.of(TokenType.ACCESS_TOKEN, 1000 * 60 * 30),
                Arguments.of(TokenType.REFRESH_TOKEN, 1000 * 60 * 60 * 24 * 7)
        );
    }

    @DisplayName(value = "생성 테스트")
    @ParameterizedTest(name = "{index}. ENUM TYPE : {0}")
    @MethodSource(value = "provideTokenTypesForConstructTest")
    public void constructTest(TokenType tokenType) {
        assertThat(tokenType).isNotNull();
    }

    @DisplayName(value = "Expiration time 반환 테스트")
    @ParameterizedTest(name = "{index}. ENUM TYPE : {0} |  Expiration time : {1}")
    @MethodSource(value = "provideTokenTypesAndExpirationTimesForGetExpirationTimeTest")
    public void getExpirationTimeTest(TokenType tokenType, long expectedExpirationTime) {
        final long expirationTime = tokenType.getExpirationTime();
        assertThat(expirationTime).isEqualTo(expectedExpirationTime);
    }

}
