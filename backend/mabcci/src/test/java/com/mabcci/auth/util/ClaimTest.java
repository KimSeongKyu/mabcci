package com.mabcci.auth.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ClaimTest {

    public static Stream<Arguments> provideClaimsForConstructTest() {
        return Arrays.stream(Claim.values())
                .map(claim -> Arguments.of(claim));
    }

    public static Stream<Arguments> provideClaimsAndKeysForGetKeyTest() {
        return Stream.of(
                Arguments.of(Claim.TOKEN_TYPE, "typ"),
                Arguments.of(Claim.HASH_ALGORITHM, "alg")
        );
    }

    @DisplayName(value = "생성 테스트")
    @ParameterizedTest(name = "{index}. ENUM TYPE : {0}")
    @MethodSource(value = "provideClaimsForConstructTest")
    public void constructTest(Claim claim) {
        // then
        assertThat(claim).isNotNull();
    }

    @DisplayName(value = "key 반환 테스트")
    @ParameterizedTest(name = "{index}. ENUM TYPE : {0} | key : {1}")
    @MethodSource(value = "provideClaimsAndKeysForGetKeyTest")
    public void getKeyTest(Claim claim, String expectedKey) {
        // when
        String key = claim.getKey();

        // then
        assertThat(key).isEqualTo(expectedKey);
    }
}
