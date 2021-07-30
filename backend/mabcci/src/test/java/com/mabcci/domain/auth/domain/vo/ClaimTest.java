package com.mabcci.domain.auth.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ClaimTest {

    static Stream<Arguments> provide_claims_and_properties_for_getter_test() {
        return Stream.of(
                Arguments.of(Claim.TOKEN_TYPE, ClaimType.HEADER, "typ", "JWT"),
                Arguments.of(Claim.HASH_ALGORITHM, ClaimType.HEADER, "alg", "HS256"),
                Arguments.of(Claim.ISSUER, ClaimType.PAYLOAD, "iss", "mabcci system"),
                Arguments.of(Claim.SUBJECT, ClaimType.PAYLOAD, "sub", "authorize member"),
                Arguments.of(Claim.AUDIENCE, ClaimType.PAYLOAD, "aud", "member")
        );
    }

    @DisplayName("Claim 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        Arrays.stream(Claim.values())
                .forEach(claim -> assertAll(
                        () -> assertThat(claim).isNotNull(),
                        () -> assertThat(claim).isExactlyInstanceOf(Claim.class)
                ));
    }

    @DisplayName("Claim 인스턴스 getter 메서드들 테스트")
    @ParameterizedTest(name = "{index}. ENUM TYPE : {0} | claim type : {1} | key : {2} | value : {3}")
    @MethodSource("provide_claims_and_properties_for_getter_test")
    void getter_test(final Claim claim, final ClaimType expectedType,
                     final String expectedKey, final String expectedValue) {
        final ClaimType type = claim.type();
        final String key = claim.key();
        final String value = claim.value();

        assertAll(
                () -> assertThat(type).isEqualTo(expectedType),
                () -> assertThat(key).isEqualTo(expectedKey),
                () -> assertThat(value).isEqualTo(expectedValue)
        );
    }
}
