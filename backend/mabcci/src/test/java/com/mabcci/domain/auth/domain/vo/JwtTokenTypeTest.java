package com.mabcci.domain.auth.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class JwtTokenTypeTest {

    public static Stream<Arguments> provideTokenTypesForConstructTest() {
        return Arrays.stream(JwtTokenType.values())
                .map(jwtTokenType -> Arguments.of(jwtTokenType));
    }

    public static Stream<Arguments> provideJwtTokenTypesAndExpirationTimesForExpirationTimeTest() {
        return Stream.of(
                Arguments.of(JwtTokenType.ACCESS_TOKEN, 1000 * 60 * 30L),
                Arguments.of(JwtTokenType.REFRESH_TOKEN, 1000 * 60 * 60 * 24 * 7L)
        );
    }

    @DisplayName(value = "생성 테스트")
    @ParameterizedTest(name = "{index}. ENUM TYPE : {0}")
    @MethodSource(value = "provideTokenTypesForConstructTest")
    public void constructTest(JwtTokenType jwtTokenType) {
        assertThat(jwtTokenType).isNotNull();
    }

    @DisplayName(value = "Expiration time 반환 테스트")
    @ParameterizedTest(name = "{index}. ENUM TYPE : {0} |  Expiration time : {1}")
    @MethodSource(value = "provideJwtTokenTypesAndExpirationTimesForExpirationTimeTest")
    public void expirationTimeTest(JwtTokenType jwtTokenType, Long expectedExpirationTime) {
        final Long expirationTime = jwtTokenType.expirationTime();
        assertThat(expirationTime).isEqualTo(expectedExpirationTime);
    }

}
