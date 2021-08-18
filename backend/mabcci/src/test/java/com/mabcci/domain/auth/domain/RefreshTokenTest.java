package com.mabcci.domain.auth.domain;

import com.mabcci.domain.auth.domain.vo.JwtToken;
import com.mabcci.global.common.Email;
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

class RefreshTokenTest {

    private RefreshToken refreshToken;

    @BeforeEach
    void setUp() {
        refreshToken = RefreshToken.builder()
                .email(EMAIL)
                .refreshToken("test.refresh.token")
                .build();
    }

    @DisplayName("RefreshToken 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(refreshToken).isNotNull(),
                () -> assertThat(refreshToken).isExactlyInstanceOf(RefreshToken.class)
        );
    }

    @DisplayName("RefreshToken 인스턴스 디폴트 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final RefreshToken refreshToken = new RefreshToken();

        assertAll(
                () -> assertThat(refreshToken).isNotNull(),
                () -> assertThat(refreshToken).isExactlyInstanceOf(RefreshToken.class)
        );
    }

    @DisplayName("RefreshToken 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(refreshToken.email()).isEqualTo(EMAIL),
                () -> assertThat(refreshToken.refreshToken()).isEqualTo(JwtToken.of("test.refresh.token"))
        );
    }

    @DisplayName("RefreshToken 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final RefreshToken invalidRefreshToken = RefreshToken.builder()
                .email("")
                .refreshToken("")
                .build();

        final Set<ConstraintViolation<RefreshToken>> invalidPropertiesOfValidRefreshToken =
                validator.validate(refreshToken);
        final Set<ConstraintViolation<RefreshToken>> invalidPropertiesOfInvalidRefreshToken =
                validator.validate(invalidRefreshToken);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidRefreshToken.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidRefreshToken.size()).isEqualTo(2)
        );
    }
}
