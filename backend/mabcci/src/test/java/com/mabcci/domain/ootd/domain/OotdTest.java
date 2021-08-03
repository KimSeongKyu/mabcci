package com.mabcci.domain.ootd.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdTest {

    public static final Ootd OOTD = Ootd.builder()
            .member(MEMBER)
            .content("content")
            .top("top")
            .bottom("bottom")
            .shoes("shoes")
            .accessory("accessory")
            .views(0L)
            .build();

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
                .views(0L)
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

    @DisplayName("Ootd 인스턴스 디폴트 생성자를 이용한 생성 여부 테스트")
    @Test
    void default_constructor_test() {
        final Ootd ootd = new Ootd();

        assertAll(
                () -> assertThat(ootd).isNotNull(),
                () -> assertThat(ootd).isExactlyInstanceOf(Ootd.class)
        );
    }

    @DisplayName("Ootd 인스턴스의 getter 메서드들 테스트")
    @Test
    void getter_test() {
        ReflectionTestUtils.setField(ootd, "id", 1L);

        assertAll(
                () -> assertThat(ootd.id()).isEqualTo(1L),
                () -> assertThat(ootd.member()).isEqualTo(MEMBER),
                () -> assertThat(ootd.content()).isEqualTo("content"),
                () -> assertThat(ootd.top()).isEqualTo("top"),
                () -> assertThat(ootd.bottom()).isEqualTo("bottom"),
                () -> assertThat(ootd.shoes()).isEqualTo("shoes"),
                () -> assertThat(ootd.accessory()).isEqualTo("accessory"),
                () -> assertThat(ootd.views()).isEqualTo(0L)
        );
    }

    @DisplayName("Ootd 인스턴스의 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Ootd invalidOotd = Ootd.builder()
                .views(-1L)
                .build();
        final int invalidPropertyCountOfValidOotd = 0;
        final int invalidPropertyCountOfInvalidOotd = 3;

        Set<ConstraintViolation<Ootd>> validationOfValidOotd = validator.validate(ootd);
        Set<ConstraintViolation<Ootd>> validationOfInvalidOotd = validator.validate(invalidOotd);

        assertAll(
                () -> assertThat(validationOfValidOotd.size()).isEqualTo(invalidPropertyCountOfValidOotd),
                () -> assertThat(validationOfInvalidOotd.size()).isEqualTo(invalidPropertyCountOfInvalidOotd)
        );
    }
}
