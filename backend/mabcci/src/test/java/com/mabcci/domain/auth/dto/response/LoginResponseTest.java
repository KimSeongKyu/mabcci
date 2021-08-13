package com.mabcci.domain.auth.dto.response;

import com.mabcci.domain.auth.domain.vo.JwtToken;
import com.mabcci.domain.auth.dto.request.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LoginResponseTest {

    private LoginResponse loginResponse;
    private JwtToken accessToken;
    private JwtToken refreshToken;

    @BeforeEach
    void setUp() {
        accessToken = JwtToken.of("test.access.token");
        refreshToken = JwtToken.of("test.refresh.token");
        loginResponse = new LoginResponse(accessToken, refreshToken);
    }

    @DisplayName("LoginResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(loginResponse).isNotNull(),
                () -> assertThat(loginResponse).isExactlyInstanceOf(LoginResponse.class)
        );
    }

    @DisplayName("LoginResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(loginResponse.accessToken()).isEqualTo(accessToken),
                () -> assertThat(loginResponse.refreshToken()).isEqualTo(refreshToken)
        );
    }

    @DisplayName("LoginResponse 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final LoginResponse invalidLoginResponse = new LoginResponse(null, null);

        final Set<ConstraintViolation<LoginResponse>> invalidPropertiesOfValidRequest =
                validator.validate(loginResponse);
        final Set<ConstraintViolation<LoginResponse>> invalidPropertiesOfInvalidRequest =
                validator.validate(invalidLoginResponse);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidRequest.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidRequest.size()).isEqualTo(2)
        );
    }
}
