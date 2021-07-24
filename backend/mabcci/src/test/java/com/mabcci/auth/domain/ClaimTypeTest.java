package com.mabcci.auth.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ClaimTypeTest {

    public static Stream<Arguments> provideClaimTypesForConstructTest() {
        return Arrays.stream(ClaimType.values())
                .map(claimType -> Arguments.of(claimType));
    }

    @DisplayName(value = "생성 테스트")
    @ParameterizedTest(name = "{index}. ENUM TYPE : {0}")
    @MethodSource(value = "provideClaimTypesForConstructTest")
    public void constructTest(ClaimType claimType) {
        // then
        assertThat(claimType).isNotNull();
    }
}
