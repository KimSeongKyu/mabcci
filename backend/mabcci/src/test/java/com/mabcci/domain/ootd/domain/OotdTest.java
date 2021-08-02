package com.mabcci.domain.ootd.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdTest {

    private Ootd ootd;
    private Validator validator;

    @BeforeEach
    void setUp() {
        ootd = Ootd.builder()
                .member(MEMBER)
                .content("content")
                .top("top")
                .bottom("bottom")
                .shoes("shoes")
                .accessory("accessory")
                .build();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @DisplayName("Ootd 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootd).isNotNull(),
                () -> assertThat(ootd).isExactlyInstanceOf(Ootd.class)
        );
    }

    @DisplayName("Ootd 인스턴스의 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(ootd.member()).isEqualTo(MEMBER),
                () -> assertThat(ootd.content()).isEqualTo("content"),
                () -> assertThat(ootd.top()).isEqualTo("top"),
                () -> assertThat(ootd.bottom()).isEqualTo("bottom"),
                () -> assertThat(ootd.shoes()).isEqualTo("shoes"),
                () -> assertThat(ootd.accessory()).isEqualTo("accessory")
        );
    }

    @DisplayName("Ootd 인스턴스의 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Ootd invalidOotd = Ootd.builder().build();
        Set<ConstraintViolation<Ootd>> validationOfValidOotd = validator.validate(ootd);
        Set<ConstraintViolation<Ootd>> validationOfInvalidOotd = validator.validate(invalidOotd);

        assertAll(
                () -> assertThat(validationOfValidOotd.size()).isEqualTo(0),
                () -> assertThat(validationOfInvalidOotd.size()).isEqualTo(2)
        );
    }
}
