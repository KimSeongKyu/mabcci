package com.mabcci.domain.auth.dto.response;

import com.mabcci.domain.auth.domain.vo.JwtToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LoginResponseTest {

    private JwtToken accessToken;
    private JwtToken refreshToken;

    @BeforeEach
    void setUp() {
        accessToken = JwtToken.of("test.access.token");
        refreshToken = JwtToken.of("test.refresh.token");
    }

    @DisplayName("LoginResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        final LoginResponse loginResponse = new LoginResponse(accessToken, refreshToken);

        assertAll(
                () -> assertThat(loginResponse).isNotNull(),
                () -> assertThat(loginResponse).isExactlyInstanceOf(LoginResponse.class)
        );
    }

    @DisplayName("LoginResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        final LoginResponse loginResponse = new LoginResponse(accessToken, refreshToken);

        assertAll(
                () -> assertThat(loginResponse.getAccessToken()).isEqualTo(accessToken),
                () -> assertThat(loginResponse.getRefreshToken()).isEqualTo(refreshToken)
        );
    }
}
