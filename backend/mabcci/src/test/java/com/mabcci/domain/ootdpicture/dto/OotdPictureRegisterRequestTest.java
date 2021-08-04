package com.mabcci.domain.ootdpicture.dto;

import com.mabcci.domain.picture.domain.Picture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdPictureRegisterRequestTest {

    private OotdPictureRegisterRequest ootdPictureRegisterRequest;
    private List<Picture> pictures;

    @BeforeEach
    void setUp() {
        pictures = new ArrayList<>(List.of(
                new Picture("testDirectory", "testFile"),
                new Picture("testDirectory", "testFile")
        ));
        ootdPictureRegisterRequest = new OotdPictureRegisterRequest(pictures);
    }

    @DisplayName("OotdPictureRegisterRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdPictureRegisterRequest).isNotNull(),
                () -> assertThat(ootdPictureRegisterRequest).isExactlyInstanceOf(OotdPictureRegisterRequest.class)
        );
    }

    @DisplayName("OOtdPictureRegisterRequest 인스턴스 getter 메서드 테스트")
    @Test
    void getter_test() {
        assertThat(ootdPictureRegisterRequest.getPictures()).isEqualTo(pictures);
    }

    @DisplayName("OOtdPictureRegisterRequest 인스턴스 프로퍼티 유효성 검사 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final OotdPictureRegisterRequest invalidRequest = new OotdPictureRegisterRequest(new ArrayList<>());

        final Set<ConstraintViolation<OotdPictureRegisterRequest>> constraintViolationsOfValidRequest =
                validator.validate(ootdPictureRegisterRequest);
        final Set<ConstraintViolation<OotdPictureRegisterRequest>> constraintViolationsOfInvalidRequest =
                validator.validate(invalidRequest);

        assertAll(
                () -> assertThat(constraintViolationsOfValidRequest.size()).isEqualTo(0),
                () -> assertThat(constraintViolationsOfInvalidRequest.size()).isEqualTo(1)
        );
    }
}
