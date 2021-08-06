package com.mabcci.domain.ootdpicture.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static com.mabcci.domain.ootd.domain.OotdTest.OOTD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class OotdPictureTest {

    public static final OotdPicture OOTD_PICTURE = OotdPicture.builder()
            .ootd(OOTD)
            .url("testUrl")
            .fileName("testFileName")
            .build();

    private OotdPicture ootdPicture;
    private Validator validator;

    @BeforeEach
    void setUp() {
        ootdPicture = OotdPicture.builder()
                .ootd(OOTD)
                .url("testUrl")
                .fileName("testFileName")
                .build();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @DisplayName("OotdPicture 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdPicture).isNotNull(),
                () -> assertThat(ootdPicture).isExactlyInstanceOf(OotdPicture.class)
        );
    }

    @DisplayName("OotdPicture 인스턴스 디폴트 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final OotdPicture ootdPicture = new OotdPicture();

        assertAll(
                () -> assertThat(ootdPicture).isNotNull(),
                () -> assertThat(ootdPicture).isExactlyInstanceOf(OotdPicture.class)
        );
    }

    @DisplayName("OotdPicture 인스턴스의 getter 메서드들 테스트")
    @Test
    void getter_test() {
        ReflectionTestUtils.setField(ootdPicture, "id", 1L);

        assertAll(
                () -> assertThat(ootdPicture.id()).isEqualTo(1L),
                () -> assertThat(ootdPicture.ootd()).isEqualTo(OOTD),
                () -> assertThat(ootdPicture.url()).isEqualTo("testUrl"),
                () -> assertThat(ootdPicture.fileName()).isEqualTo("testFileName")
        );
    }

    @DisplayName("OotdPicture 인스턴스의 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final OotdPicture invalidOotdPicture = OotdPicture.builder()
                .ootd(null)
                .url("")
                .fileName("")
                .build();
        final Set<ConstraintViolation<OotdPicture>> invalidPropertiesOfValidOotdPicture = validator.validate(ootdPicture);
        final Set<ConstraintViolation<OotdPicture>> invalidPropertiesOfInvalidOotdPicture = validator.validate(invalidOotdPicture);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidOotdPicture.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidOotdPicture.size()).isEqualTo(3)
        );

    }
}