package com.mabcci.domain.auth.domain.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class JwtTokenTest {

    private JwtToken jwtToken;

    @BeforeEach
    void setUp() {
        jwtToken = JwtToken.of("test.jwt.token");
    }

    @DisplayName("JwtToken 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(jwtToken).isNotNull(),
                () -> assertThat(jwtToken).isExactlyInstanceOf(JwtToken.class)
        );
    }

    @DisplayName("JwtToken 인스턴스 디폴트 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final JwtToken jwtToken = new JwtToken();

        assertAll(
                () -> assertThat(jwtToken).isNotNull(),
                () -> assertThat(jwtToken).isExactlyInstanceOf(JwtToken.class)
        );
    }

    @DisplayName("JwtToken 인스턴스 문자열 값 반환 테스트")
    @Test
    void jwtToken_test() {
        assertThat(jwtToken.jwtToken()).isEqualTo("test.jwt.token");
    }

    @DisplayName("JwtToken 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final JwtToken invalidJwtToken = JwtToken.of("");

        final Set<ConstraintViolation<JwtToken>> invalidPropertiesOfValidJwtToken =
                validator.validate(jwtToken);
        final Set<ConstraintViolation<JwtToken>> invalidPropertiesOfInvalidJwtToken =
                validator.validate(invalidJwtToken);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidJwtToken.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidJwtToken.size()).isEqualTo(1)
        );
    }

    @DisplayName("JwtToken 인스턴스 equals() & hashCode() 테스트")
    @Test
    void equals_and_hashCode_test() {
        final JwtToken other = JwtToken.of("test.jwt.token");

        assertAll(
                () -> assertThat(jwtToken).isEqualTo(other),
                () -> assertThat(jwtToken.hashCode()).isEqualTo(other.hashCode())
        );
    }
}
