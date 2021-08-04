package com.mabcci.domain.picture.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PictureTest {

    private Picture picture;

    @BeforeEach
    void setUp() {
        picture = new Picture("testDirectoryName", "testFileName");
    }

    @DisplayName("Picture 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(picture).isNotNull(),
                () -> assertThat(picture).isExactlyInstanceOf(Picture.class)
        );
    }
}
