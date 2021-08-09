package com.mabcci.domain.auth.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LoginRequestTest {

    @DisplayName("LoginRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        final LoginRequest loginRequest = new LoginRequest(EMAIL, PASSWORD);

        assertAll(
                () -> assertThat(loginRequest).isNotNull(),
                () -> assertThat(loginRequest).isExactlyInstanceOf(LoginRequest.class)
        );
    }

    @DisplayName("LoginRequest 인스턴스 getter들 테스트")
    @Test
    void getter_test() {
        final LoginRequest loginRequest = new LoginRequest(EMAIL, PASSWORD);

        assertAll(
                () -> assertThat(loginRequest.getEmail()).isEqualTo(EMAIL),
                () -> assertThat(loginRequest.getPassword()).isEqualTo(PASSWORD)
        );
    }
}
