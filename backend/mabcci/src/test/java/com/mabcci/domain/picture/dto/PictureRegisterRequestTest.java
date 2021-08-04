package com.mabcci.domain.picture.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Set;

import static com.mabcci.domain.picture.common.PictureUtilTest.PICTURE_FILES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PictureRegisterRequestTest {

    private PictureRegisterRequest pictureRegisterRequest;

    @BeforeEach
    void setUp() {
        pictureRegisterRequest = new PictureRegisterRequest(PICTURE_FILES);
    }

    @DisplayName("PictureRegisterRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(pictureRegisterRequest).isNotNull(),
                () -> assertThat(pictureRegisterRequest).isExactlyInstanceOf(PictureRegisterRequest.class)
        );
    }

    @DisplayName("PictureRegisterRequest 인스턴스 getter 메서드 테스트")
    @Test
    void getter_test() {
        assertThat(pictureRegisterRequest.getPictures()).isEqualTo(PICTURE_FILES);
    }

    @DisplayName("PictureRegisterRequest 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final PictureRegisterRequest invalidPictureRegisterRequest = new PictureRegisterRequest(new ArrayList<>());

        final Set<ConstraintViolation<PictureRegisterRequest>> invalidPropertiesOfValidPictureRegisterRequest
                = validator.validate(pictureRegisterRequest);
        final Set<ConstraintViolation<PictureRegisterRequest>> invalidPropertiesOfInvalidPictureRegisterRequest
                = validator.validate(invalidPictureRegisterRequest);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidPictureRegisterRequest.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidPictureRegisterRequest.size()).isEqualTo(1)
        );
    }
}
