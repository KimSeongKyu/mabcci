package com.mabcci.domain.auth.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.domain.model.EmailTest.EMAIL;
import static com.mabcci.domain.model.PasswordTest.PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class LoginRequestTest {

    @DisplayName("생성 테스트")
    @Test
    public void constructTest() {
        final LoginRequest loginRequest = new LoginRequest(EMAIL, PASSWORD);

        assertAll(
                () -> assertThat(loginRequest).isNotNull(),
                () -> assertThat(loginRequest).isExactlyInstanceOf(LoginRequest.class)
        );
    }

    @DisplayName("getter 테스트")
    @Test
    public void getterTest() {
        final LoginRequest loginRequest = new LoginRequest(EMAIL, PASSWORD);

        assertAll(
                () -> assertThat(loginRequest.getEmail()).isEqualTo(EMAIL),
                () -> assertThat(loginRequest.getPassword()).isEqualTo(PASSWORD)
        );
    }
}
