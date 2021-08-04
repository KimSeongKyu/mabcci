package com.mabcci.domain.ootd.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.mabcci.domain.picture.common.PictureUtilTest.PICTURE_FILES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdRegisterRequestTest {

    private OotdRegisterRequest ootdRegisterRequest;

    @BeforeEach
    void setUp() {
        ootdRegisterRequest = new OotdRegisterRequest(
                "닉네임", "내용", "상의", "하의", "신발", "악세사리",
                PICTURE_FILES, new ArrayList<>(List.of("해시태그1", "해시태그2"))
        );
    }

    @DisplayName("OotdRegisterRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdRegisterRequest).isNotNull(),
                () -> assertThat(ootdRegisterRequest).isExactlyInstanceOf(OotdRegisterRequest.class)
        );
    }

    @DisplayName("OotdRegisterRequest 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(ootdRegisterRequest.getNickname()).isEqualTo("닉네임"),
                () -> assertThat(ootdRegisterRequest.getContent()).isEqualTo("내용"),
                () -> assertThat(ootdRegisterRequest.getTop()).isEqualTo("상의"),
                () -> assertThat(ootdRegisterRequest.getBottom()).isEqualTo("하의"),
                () -> assertThat(ootdRegisterRequest.getShoes()).isEqualTo("신발"),
                () -> assertThat(ootdRegisterRequest.getAccessory()).isEqualTo("악세사리"),
                () -> assertThat(ootdRegisterRequest.getPictures()).isEqualTo(PICTURE_FILES),
                () -> assertThat(ootdRegisterRequest.getHashtags()).isEqualTo(new ArrayList<>(List.of("해시태그1", "해시태그2"))
        ));
    }

    @DisplayName("OotdRegisterRequest 인스턴스 프토퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final OotdRegisterRequest invalidRequest = new OotdRegisterRequest(
                null, null, null, null, null, null,
                new ArrayList<>(), null);

        final Set<ConstraintViolation<OotdRegisterRequest>> invalidPropertiesOfValidRequest =
                validator.validate(ootdRegisterRequest);
        final Set<ConstraintViolation<OotdRegisterRequest>> invalidPropertiesOfInvalidRequest =
                validator.validate(invalidRequest);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidRequest.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidRequest.size()).isEqualTo(2)
        );
    }
}
