package com.mabcci.domain.auth.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static com.mabcci.global.common.EmailTest.EMAIL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LogoutRequestTest {

    private LogoutRequest logoutRequest;

    @BeforeEach
    void setUp() {
        logoutRequest = new LogoutRequest(EMAIL);
    }

    @DisplayName("LogoutRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(logoutRequest).isNotNull(),
                () -> assertThat(logoutRequest).isExactlyInstanceOf(LogoutRequest.class)
        );
    }

    @DisplayName("LogoutRequest 인스턴스 getter 메서드 테스트")
    @Test
    void getter_test() {
        assertThat(logoutRequest.email()).isEqualTo(EMAIL);
    }


    @DisplayName("LogoutRequest 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final LogoutRequest invalidLogoutRequest = new LogoutRequest(null);

        final Set<ConstraintViolation<LogoutRequest>> invalidPropertiesOfValidRequest =
                validator.validate(logoutRequest);
        final Set<ConstraintViolation<LogoutRequest>> invalidPropertiesOfInvalidRequest =
                validator.validate(invalidLogoutRequest);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidRequest.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidRequest.size()).isEqualTo(1)
        );
    }
}
