package com.mabcci.domain.picture.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
