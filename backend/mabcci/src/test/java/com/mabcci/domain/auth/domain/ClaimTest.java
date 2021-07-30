package com.mabcci.domain.auth.domain;

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

    public static Stream<Arguments> provideClaimsAndTypesForGetTypeTest() {
        return Stream.of(
                Arguments.of(Claim.TOKEN_TYPE, ClaimType.HEADER),
                Arguments.of(Claim.HASH_ALGORITHM, ClaimType.HEADER),
                Arguments.of(Claim.ISSUER, ClaimType.PAYLOAD),
                Arguments.of(Claim.SUBJECT, ClaimType.PAYLOAD),
                Arguments.of(Claim.AUDIENCE, ClaimType.PAYLOAD)
        );
    }

    public static Stream<Arguments> provideClaimsAndKeysForGetKeyTest() {
        return Stream.of(
                Arguments.of(Claim.TOKEN_TYPE, "typ"),
                Arguments.of(Claim.HASH_ALGORITHM, "alg"),
                Arguments.of(Claim.ISSUER, "iss"),
                Arguments.of(Claim.SUBJECT, "sub"),
                Arguments.of(Claim.AUDIENCE, "aud")
        );
    }

    public static Stream<Arguments> provideClaimsAndValuesForGetValueTest() {
        return Stream.of(
                Arguments.of(Claim.TOKEN_TYPE, "JWT"),
                Arguments.of(Claim.HASH_ALGORITHM, "HS256"),
                Arguments.of(Claim.ISSUER, "mabcci system"),
                Arguments.of(Claim.SUBJECT, "authorize member"),
                Arguments.of(Claim.AUDIENCE, "member")
        );
    }

    @DisplayName(value = "생성 테스트")
    @ParameterizedTest(name = "{index}. ENUM TYPE : {0}")
    @MethodSource(value = "provideClaimsForConstructTest")
    public void constructTest(Claim claim) {
        assertThat(claim).isNotNull();
    }

    @DisplayName(value = "type 반환 테스트")
    @ParameterizedTest(name = "{index}. ENUM TYPE : {0} | claim type : {1}")
    @MethodSource(value = "provideClaimsAndTypesForGetTypeTest")
    public void getTypeTest(Claim claim, ClaimType expectedType) {
        final ClaimType type = claim.getType();
        assertThat(type).isEqualTo(expectedType);
    }

    @DisplayName(value = "key 반환 테스트")
    @ParameterizedTest(name = "{index}. ENUM TYPE : {0} | key : {1}")
    @MethodSource(value = "provideClaimsAndKeysForGetKeyTest")
    public void getKeyTest(Claim claim, String expectedKey) {
        final String key = claim.getKey();
        assertThat(key).isEqualTo(expectedKey);
    }

    @DisplayName(value = "value 반환 테스트")
    @ParameterizedTest(name = "{index}. ENUM TYPE : {0} | value : {1}")
    @MethodSource(value = "provideClaimsAndValuesForGetValueTest")
    public void getValueTest(Claim claim, String expectedValue) {
        final String value = claim.getValue();
        assertThat(value).isEqualTo(expectedValue);
    }
}
