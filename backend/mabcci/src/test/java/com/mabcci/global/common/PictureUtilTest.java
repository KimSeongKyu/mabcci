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
                () -> assertThat(pictureUtil.makeFileExtension(pngPicture.getContentType())).isEqualTo(".png"),
                () -> assertThat(pictureUtil.makeFileExtension(jpegPicture.getContentType())).isEqualTo(".jpg")
        );
    }

    @DisplayName("PictureUtil 인스턴스 절대경로 생성 테스트")
    @Test
    void make_absolute_path_test() {
        final String absolutePath = pictureUtil.makeAbsolutePath();
        assertThat(absolutePath).isEqualTo(new File("").getAbsolutePath() + File.separator + File.separator);
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
        final String directoryName = "testDirectory";
        pictureUtil.makeDirectory(directoryName);

        final File directory = new File(directoryName);
        assertThat(directory.exists()).isTrue();

        directory.delete();
    }

    @DisplayName("PictureUtil 인스턴스 사진 저장 테스트")
    @Test
    void save_picture_test() throws Exception {
        final String directoryName = "testDirectory";
        pictureUtil.makeDirectory(directoryName);

        final MultipartFile picture = mockPictures.get(0);
        final Picture savedPicture = pictureUtil.savePicture(picture, directoryName);

        assertAll(
                () -> assertThat(savedPicture).isNotNull(),
                () -> assertThat(savedPicture).isExactlyInstanceOf(Picture.class)
        );

        final File testFolder = new File(directoryName);
        final File[] filesInTestFolder = testFolder.listFiles();
        for (File file : filesInTestFolder) {
            file.delete();
        }
        testFolder.delete();
    }
}