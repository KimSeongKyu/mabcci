package com.mabcci.domain.picture.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PictureTest {

    private Picture picture;
    private Validator validator;

    @BeforeEach
    void setUp() {
        picture = new Picture("testDirectoryName", "testFileName");
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @DisplayName("Picture 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(picture).isNotNull(),
                () -> assertThat(picture).isExactlyInstanceOf(Picture.class)
        );
    }

    @DisplayName("Picture 인스턴스 디폴트 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final Picture picture = new Picture();

        assertAll(
                () -> assertThat(picture).isNotNull(),
                () -> assertThat(picture).isExactlyInstanceOf(Picture.class)
        );
    }

    @DisplayName("Picture 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(picture.url()).isEqualTo("testDirectoryName"),
                () -> assertThat(picture.fileName()).isEqualTo("testFileName")
        );
    }

    @DisplayName("Picture 인스턴스 파일 경로 반환 테스트")
    @Test
    void path_test() {
        assertThat(picture.path()).isEqualTo("testDirectoryName/testFileName");
    }

    @DisplayName("Picture 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Picture invalidPicture = new Picture(null, "");
        final Set<ConstraintViolation<Picture>> invalidPropertiesOfValidPicture = validator.validate(picture);
        final Set<ConstraintViolation<Picture>> invalidPropertiesOfInvalidPicture = validator.validate(invalidPicture);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidPicture.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidPicture.size()).isEqualTo(2)
        );
    }
}
