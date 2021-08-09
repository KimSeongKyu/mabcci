package com.mabcci.domain.picture.common;

import com.mabcci.domain.picture.domain.Picture;
import com.mabcci.domain.picture.domain.PictureType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PictureUtilTest {

    public static final List<MultipartFile> PICTURE_FILES = new ArrayList<>(List.of(
            new MockMultipartFile(
                    "pngPicture",
                    "pngPicture.png",
                    MediaType.IMAGE_PNG_VALUE,
                    "testPngPicture".getBytes()),
            new MockMultipartFile(
                    "jpegPicture",
                    "jpegPicture.jpeg",
                    MediaType.IMAGE_JPEG_VALUE,
                    "testJpegPicture".getBytes())
    ));

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
        ReflectionTestUtils.setField(pictureUtil, "baseDirectory", "C:/mabcci/images/local");
        final String directoryName = pictureUtil.makeDirectoryName(PictureType.OOTD);
        final String regexOfyyyyMMdd = "(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])";

        assertAll(
                () -> assertThat(directoryName).contains("C:/mabcci/images/local"),
                () -> assertThat(directoryName).containsPattern(regexOfyyyyMMdd)
        );
    }

    @DisplayName("PictureUtil 인스턴스 디렉토리 이름을 url로 변경하는 기능 테스트")
    @Test
    void map_directory_name_to_url_test() {
        final String baseUrl = Paths.get( "images").toString();
        final String baseDirectory =
                Paths.get("C:", File.separator, "mabcci", File.separator, "images", File.separator, "local")
                        .toString();
        ReflectionTestUtils.setField(pictureUtil, "baseUrl", baseUrl);
        ReflectionTestUtils.setField(pictureUtil, "baseDirectory", baseDirectory);
        final String directoryName = baseDirectory.concat(File.separator);

        final String url = pictureUtil.mapDirectoryNameToUrl(directoryName);

        assertAll(
                () -> assertThat(url).contains(baseUrl),
                () -> assertThat(url).contains("/"),
                () -> assertThat(url).doesNotContain(baseDirectory),
                () -> assertThat(url).doesNotContain(File.separator)
        );
    }

    @DisplayName("PictureUtil 인스턴스 url을 디렉토리 이름으로 변경하는 기능 테스트")
    @Test
    void map_url_to_directory_name_test() {
        final String baseUrl = Paths.get( "images").toString();
        final String baseDirectory =
                Paths.get("C:", File.separator, "mabcci", File.separator, "images", File.separator, "local")
                        .toString();
        System.out.println(baseDirectory);
        ReflectionTestUtils.setField(pictureUtil, "baseUrl", baseUrl);
        ReflectionTestUtils.setField(pictureUtil, "baseDirectory", baseDirectory);
        final String url = baseUrl.concat("/");

        final String directoryName = pictureUtil.mapUrlToDirectoryName(url);

        assertAll(
                () -> assertThat(directoryName).contains(baseDirectory),
                () -> assertThat(directoryName).contains(File.separator),
                () -> assertThat(directoryName).doesNotContain("/")
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
        ReflectionTestUtils.setField(pictureUtil, "baseDirectory", "C:/mabcci/images/local");
        final String directoryName = pictureUtil.makeDirectory(PictureType.OOTD);

        final File directory = new File(directoryName);
        assertThat(directory.exists()).isTrue();

        directory.delete();
    }

    @DisplayName("PictureUtil 인스턴스 사진 저장 테스트")
    @Test
    void save_picture_test() throws Exception {
        ReflectionTestUtils.setField(pictureUtil, "baseUrl", "/images");
        ReflectionTestUtils.setField(pictureUtil, "baseDirectory", "C:/mabcci/images/local");
        final String directoryName = pictureUtil.makeDirectory(PictureType.OOTD);

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