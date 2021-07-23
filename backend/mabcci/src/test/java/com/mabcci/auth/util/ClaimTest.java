package com.mabcci.auth.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ClaimTest {

    @DisplayName(value = "생성 테스트")
    @Test
    public void constructTest() {
        // given and when
        Claim[] claims = Claim.values();

        // then
        for(Claim claim : claims) {
            assertThat(claim).isNotNull();
        }
    }
}
