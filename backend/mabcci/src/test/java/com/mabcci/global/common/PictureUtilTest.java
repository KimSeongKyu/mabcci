package com.mabcci.global.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PictureUtilTest {

    private PictureUtil pictureUtil;
    private List<MultipartFile> mockPictures;

    @BeforeEach
    void setUp() {
        pictureUtil = new PictureUtil();
        mockPictures = new ArrayList<>();

        mockPictures.add(new MockMultipartFile(
                "pngPicture",
                "pngPicture.png",
                MediaType.IMAGE_PNG_VALUE,
                "testPngPicture".getBytes()
        ));

        mockPictures.add(new MockMultipartFile(
                "jpegPicture",
                "jpegPicture.jpeg",
                MediaType.IMAGE_JPEG_VALUE,
                "testJpegPicture".getBytes()
        ));
    }

    @DisplayName("PictureUtil 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(pictureUtil).isNotNull(),
                () -> assertThat(pictureUtil).isExactlyInstanceOf(PictureUtil.class)
        );
    }

    @DisplayName("PictureUtil 인스턴스 파일을 Picture 엔티티로 변환하는 테스트")
    @Test
    void to_entities_test() {
        final List<Picture> entities = pictureUtil.toEntities(mockPictures);
        assertThat(entities.size()).isEqualTo(2);
    }

    @DisplayName("PictureUtil 인스턴스 전달받은 사진이 없는 경우 빈 리스트를 반환하는 테스트")
    @Test
    void return_empty_list_when_no_picture() {
        final List<MultipartFile> pictures = new ArrayList<>();
        final List<Picture> entities = pictureUtil.toEntities(pictures);
        assertThat(entities).isEmpty();
    }

    @DisplayName("PictureUtil 인스턴스 디렉토리 이름 생성 테스트")
    @Test
    void make_directory_name_test() {
        final String directoryName = pictureUtil.makeDirectoryName();
        final String regexOfyyyyMMdd = "(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])";

        assertAll(
                () -> assertThat(directoryName).contains("images"),
                () -> assertThat(directoryName).containsPattern(regexOfyyyyMMdd)
        );
    }

    @DisplayName("PictureUtil 인스턴스 확장자명 생성 테스트")
    @Test
    void make_original_file_extension_test() {
        final MultipartFile pngPicture = mockPictures.get(0);
        final MultipartFile jpegPicture = mockPictures.get(1);

        assertAll(
                () -> assertThat(pictureUtil.makeOriginalFileExtension(pngPicture.getContentType())).isEqualTo(".png"),
                () -> assertThat(pictureUtil.makeOriginalFileExtension(jpegPicture.getContentType())).isEqualTo(".jpg")
        );
    }

    @DisplayName("PictureUtil 인스턴스 파일 이름 생성 테스트")
    @Test
    void make_file_name_test() {
        final String fileName = pictureUtil.makeFileName(".png");
        final String[] fileNameSplitByComma = fileName.split("\\.");

        assertAll(
                () -> assertThat(Long.valueOf(fileNameSplitByComma[0])).isLessThan(System.nanoTime()),
                () -> assertThat(fileNameSplitByComma[1]).isEqualTo("png")
        );
    }

    @DisplayName("PictureUtil 인스턴스 디렉토리 생성 테스트")
    @Test
    void make_directory_test() {
        final boolean isMadeDirectory = pictureUtil.makeDirectory("testDirectory");
        assertThat(isMadeDirectory).isTrue();
        new File("testDirectory").delete();
    }

    @DisplayName("PictureUtil 인스턴스 파일 저장 테스트")
    @Test
    void save_file_test() {
        final MultipartFile picture = mockPictures.get(0);
        final Picture savedPicture = pictureUtil.saveFile(picture);

        assertAll(
                () -> assertThat(savedPicture).isNotNull(),
                () -> assertThat(savedPicture).isExactlyInstanceOf(Picture.class)
        );
    }
}