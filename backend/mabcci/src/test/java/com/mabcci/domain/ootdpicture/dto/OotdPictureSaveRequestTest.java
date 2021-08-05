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

import static com.mabcci.domain.ootd.domain.OotdTest.OOTD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdPictureSaveRequestTest {

    private OotdPictureSaveRequest ootdPictureSaveRequest;
    private List<Picture> pictures;

    @BeforeEach
    void setUp() {
        pictures = new ArrayList<>(List.of(
                new Picture("testDirectory", "testFile"),
                new Picture("testDirectory", "testFile")
        ));
        ootdPictureSaveRequest = new OotdPictureSaveRequest(OOTD, pictures);
    }

    @DisplayName("OotdPictureSaveRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdPictureSaveRequest).isNotNull(),
                () -> assertThat(ootdPictureSaveRequest).isExactlyInstanceOf(OotdPictureSaveRequest.class)
        );
    }

    @DisplayName("OotdPictureSaveRequest 인스턴스 getter 메서드 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(ootdPictureSaveRequest.getPictures()).isEqualTo(pictures),
                () -> assertThat(ootdPictureSaveRequest.getOotd()).isEqualTo(OOTD)
        );

    }

    @DisplayName("OotdPictureSaveRequest 인스턴스 프로퍼티 유효성 검사 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final OotdPictureSaveRequest invalidRequest = new OotdPictureSaveRequest(null, new ArrayList<>());

        final Set<ConstraintViolation<OotdPictureSaveRequest>> constraintViolationsOfValidRequest =
                validator.validate(ootdPictureSaveRequest);
        final Set<ConstraintViolation<OotdPictureSaveRequest>> constraintViolationsOfInvalidRequest =
                validator.validate(invalidRequest);

        assertAll(
                () -> assertThat(constraintViolationsOfValidRequest.size()).isEqualTo(0),
                () -> assertThat(constraintViolationsOfInvalidRequest.size()).isEqualTo(2)
        );
    }
}
