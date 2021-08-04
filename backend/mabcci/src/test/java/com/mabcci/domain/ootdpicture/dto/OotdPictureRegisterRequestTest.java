package com.mabcci.domain.ootdpicture.dto;

import com.mabcci.domain.picture.domain.Picture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
}
