package com.mabcci.domain.ootd.dto.request;

import com.mabcci.global.common.Nickname;
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
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdWithPicturesAndHashtagsRegisterRequestTest {

    private OotdWithPicturesAndHashtagsRegisterRequest request;

    @BeforeEach
    void setUp() {
        request = new OotdWithPicturesAndHashtagsRegisterRequest(
                NICKNAME, "내용", "상의", "하의", "신발", "악세사리",
                PICTURE_FILES, new ArrayList<>(List.of("해시태그1", "해시태그2"))
        );
    }

    @DisplayName("OotdWithPicturesAndHashtagsRegisterRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(request).isNotNull(),
                () -> assertThat(request).isExactlyInstanceOf(OotdWithPicturesAndHashtagsRegisterRequest.class)
        );
    }

    @DisplayName("OotdWithPicturesAndHashtagsRegisterRequest 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(request.getNickname()).isEqualTo(NICKNAME),
                () -> assertThat(request.getContent()).isEqualTo("내용"),
                () -> assertThat(request.getTop()).isEqualTo("상의"),
                () -> assertThat(request.getBottom()).isEqualTo("하의"),
                () -> assertThat(request.getShoes()).isEqualTo("신발"),
                () -> assertThat(request.getAccessory()).isEqualTo("악세사리"),
                () -> assertThat(request.getPictures()).isEqualTo(PICTURE_FILES),
                () -> assertThat(request.getHashtags()).isEqualTo(new ArrayList<>(List.of("해시태그1", "해시태그2"))
                ));
    }

    @DisplayName("OotdWithPicturesAndHashtagsRegisterRequest 인스턴스 프토퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final OotdWithPicturesAndHashtagsRegisterRequest invalidRequest = new OotdWithPicturesAndHashtagsRegisterRequest(
                Nickname.of(null), null, null, null, null, null,
                new ArrayList<>(), null);

        final Set<ConstraintViolation<OotdWithPicturesAndHashtagsRegisterRequest>> invalidPropertiesOfValidRequest =
                validator.validate(request);
        final Set<ConstraintViolation<OotdWithPicturesAndHashtagsRegisterRequest>> invalidPropertiesOfInvalidRequest =
                validator.validate(invalidRequest);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidRequest.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidRequest.size()).isEqualTo(2)
        );
    }
}
