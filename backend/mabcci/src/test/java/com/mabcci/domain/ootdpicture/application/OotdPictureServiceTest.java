package com.mabcci.domain.ootdpicture.application;

import com.mabcci.domain.ootdpicture.domain.OotdPictureRepository;
import com.mabcci.domain.ootdpicture.dto.OotdPictureRegisterRequest;
import com.mabcci.domain.picture.domain.Picture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.mabcci.domain.ootd.domain.OotdTest.OOTD;
import static com.mabcci.domain.ootdpicture.domain.OotdPictureTest.OOTD_PICTURE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OotdPictureServiceTest {

    @InjectMocks
    private OotdPictureService ootdPictureService;

    @Mock
    private OotdPictureRepository ootdPictureRepository;

    @DisplayName("OotdPictureService 인스턴스 ootd 사진 등록 테스트")
    @Test
    void register_ootd_picture_test() {
        final List<Picture> pictures = new ArrayList<>(List.of(
                new Picture("testDirectory", "testFileName"),
                new Picture("testDirectory", "testFileName")
        ));
        final OotdPictureRegisterRequest ootdPictureRegisterRequest =
                new OotdPictureRegisterRequest(OOTD, pictures);
        doReturn(OOTD_PICTURE).when(ootdPictureRepository).save(any());

        ootdPictureService.registerOotdPictures(ootdPictureRegisterRequest);

        verify(ootdPictureRepository, times(pictures.size())).save(any());
    }
}
