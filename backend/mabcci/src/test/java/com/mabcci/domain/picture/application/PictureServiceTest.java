package com.mabcci.domain.picture.application;

import com.mabcci.domain.picture.common.PictureUtil;
import com.mabcci.domain.picture.domain.Picture;
import com.mabcci.domain.picture.dto.PictureSaveRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.mabcci.domain.picture.common.PictureUtilTest.PICTURE_FILES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PictureServiceTest {

    @InjectMocks
    private PictureService pictureService;

    @Mock
    private PictureUtil pictureUtil;

    @DisplayName("PictureService 인스턴스 사진 저장 테스트")
    @Test
    void save_pictures_test() {
        final PictureSaveRequest pictureSaveRequest = new PictureSaveRequest(PICTURE_FILES);
        final String directoryName = "testDirectory";
        final String fileName = "testFile";

        doReturn(directoryName).when(pictureUtil).makeDirectoryName();
        doNothing().when(pictureUtil).makeDirectory(any());
        doReturn(new Picture(directoryName, fileName)).when(pictureUtil).savePicture(any(), any());

        final List<Picture> pictures = pictureService.savePictures(pictureSaveRequest);

        verify(pictureUtil, times(1)).makeDirectory(any());
        verify(pictureUtil, times(PICTURE_FILES.size())).savePicture(any(), any());

        assertAll(
                () -> assertThat(pictures.size()).isEqualTo(PICTURE_FILES.size()),
                () -> pictures.stream()
                        .forEach(picture -> {
                            assertThat(picture.url()).isEqualTo(directoryName);
                            assertThat(picture.fileName()).isEqualTo(fileName);
                        })
        );
    }
}
