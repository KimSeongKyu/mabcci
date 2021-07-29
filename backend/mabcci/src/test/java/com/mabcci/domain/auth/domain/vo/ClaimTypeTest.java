package com.mabcci.domain.auth.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ClaimTypeTest {

    @DisplayName("ClaimType 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        Arrays.stream(ClaimType.values())
                .forEach(claimType -> assertAll(
                        () -> assertThat(claimType).isNotNull(),
                        () -> assertThat(claimType).isExactlyInstanceOf(ClaimType.class)
                ));
    }
}
