package com.mabcci.domain.auth.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class LoginResponseTest {

    @DisplayName("LoginResponse 인스턴스 생성 여부 테스트")
    @Test
    public void constructTest() {
        final String accessToken = "test.access.token";
        final String refreshToken = "test.refresh.token";
        final LoginResponse loginResponse = new LoginResponse(accessToken, refreshToken);

        assertAll(
                () -> assertThat(loginResponse).isNotNull(),
                () -> assertThat(loginResponse).isExactlyInstanceOf(LoginResponse.class)
        );
    }

    @DisplayName("LoginResponseTest getter 메서드들 테스트")
    @Test
    public void getterTest() {
        final String expectedAccessToken = "test.access.token";
        final String expectedRefreshToken = "test.refresh.token";
        final LoginResponse loginResponse = new LoginResponse(expectedAccessToken, expectedRefreshToken);

        assertAll(
                () -> assertThat(loginResponse.getAccessToken()).isEqualTo(expectedAccessToken),
                () -> assertThat(loginResponse.getRefreshToken()).isEqualTo(expectedRefreshToken)
        );
    }
}
