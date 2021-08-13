package com.mabcci.domain.auth.dto.request;

import com.mabcci.global.common.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LoginRequestTest {

    private LoginRequest loginRequest;

    @BeforeEach
    void setUp() {
        loginRequest = new LoginRequest(EMAIL, PASSWORD);
    }

    @DisplayName("LoginRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(loginRequest).isNotNull(),
                () -> assertThat(loginRequest).isExactlyInstanceOf(LoginRequest.class)
        );
    }

    @DisplayName("LoginRequest 인스턴스 getter들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(loginRequest.email()).isEqualTo(EMAIL),
                () -> assertThat(loginRequest.password()).isEqualTo(PASSWORD)
        );
    }

    @DisplayName("LoginRequest 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final LoginRequest invalidLoginRequest = new LoginRequest(null, null);

        final Set<ConstraintViolation<LoginRequest>> invalidPropertiesOfValidRequest =
                validator.validate(loginRequest);
        final Set<ConstraintViolation<LoginRequest>> invalidPropertiesOfInvalidRequest =
                validator.validate(invalidLoginRequest);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidRequest.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidRequest.size()).isEqualTo(2)
        );
    }
}
