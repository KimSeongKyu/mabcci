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

class PictureSaveRequestTest {

    private PictureSaveRequest pictureSaveRequest;

    @BeforeEach
    void setUp() {
        pictureSaveRequest = new PictureSaveRequest(PICTURE_FILES);
    }

    @DisplayName("PictureSaveRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(pictureSaveRequest).isNotNull(),
                () -> assertThat(pictureSaveRequest).isExactlyInstanceOf(PictureSaveRequest.class)
        );
    }

    @DisplayName("PictureSaveRequest 인스턴스 getter 메서드 테스트")
    @Test
    void getter_test() {
        assertThat(pictureSaveRequest.getPictures()).isEqualTo(PICTURE_FILES);
    }

    @DisplayName("PictureSaveRequest 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final PictureSaveRequest invalidPictureSaveRequest = new PictureSaveRequest(new ArrayList<>());

        final Set<ConstraintViolation<PictureSaveRequest>> invalidPropertiesOfValidPictureRegisterRequest
                = validator.validate(pictureSaveRequest);
        final Set<ConstraintViolation<PictureSaveRequest>> invalidPropertiesOfInvalidPictureRegisterRequest
                = validator.validate(invalidPictureSaveRequest);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidPictureRegisterRequest.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidPictureRegisterRequest.size()).isEqualTo(1)
        );
    }
}
