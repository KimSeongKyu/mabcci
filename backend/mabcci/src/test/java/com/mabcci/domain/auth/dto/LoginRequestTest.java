package com.mabcci.domain.auth.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class LoginRequestTest {

    @DisplayName(value = "생성 테스트")
    @Test
    public void constructTest() {
        // given
        String email = "example@example.com";
        String password = "testPassword";

        // when
        LoginRequest loginRequest = new LoginRequest(email, password);

        // then
        assertAll(
                () -> assertThat(loginRequest).isNotNull(),
                () -> assertThat(loginRequest).isExactlyInstanceOf(LoginRequest.class)
        );
    }
}
