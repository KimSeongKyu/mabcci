package com.mabcci.global.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PictureUtilTest {

    private PictureUtil pictureUtil;

    @BeforeEach
    void setUp() {
        pictureUtil = new PictureUtil();
    }

    @DisplayName("PictureUtil 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(pictureUtil).isNotNull(),
                () -> assertThat(pictureUtil).isExactlyInstanceOf(PictureUtil.class)
        );
    }

    @DisplayName("PictureUtil 인스턴스 전달받은 사진이 없는 경우 빈 리스트를 반환하는 테스트")
    @Test
    void return_empty_list_when_no_picture() {
        List<MultipartFile> pictures = new ArrayList<>();
        List<Picture> pictureEntities = pictureUtil.toEntities(pictures);

        assertThat(pictureEntities).isEmpty();
    }
}
