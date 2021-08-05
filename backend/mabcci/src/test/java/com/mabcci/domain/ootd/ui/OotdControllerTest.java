package com.mabcci.domain.ootd.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mabcci.domain.hashtag.application.HashtagService;
import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.hashtag.dto.HashtagSaveResponse;
import com.mabcci.domain.ootd.application.OotdService;
import com.mabcci.domain.ootdhashtag.application.OotdHashtagService;
import com.mabcci.domain.ootdpicture.application.OotdPictureService;
import com.mabcci.domain.picture.application.PictureService;
import com.mabcci.domain.picture.domain.Picture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.mabcci.domain.ootd.domain.OotdTest.OOTD;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OotdController.class)
class OotdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OotdService ootdService;

    @MockBean
    private PictureService pictureService;

    @MockBean
    private OotdPictureService ootdPictureService;

    @MockBean
    private HashtagService hashtagService;

    @MockBean
    private OotdHashtagService ootdHashtagService;

    @DisplayName("OotdController 인스턴스 ootd 등록 테스트")
    @Test
    void register_ootd_test() throws Exception {
        final List<Picture> pictures = new ArrayList<>(List.of(
                new Picture("testUrl1", "testFileName1"),
                new Picture("testUrl2", "testFileName2")
        ));
        final List<Hashtag> hashtags = new ArrayList<>(List.of(
                Hashtag.builder()
                        .name("해시태그1")
                        .build(),
                Hashtag.builder()
                        .name("해시태그2")
                        .build()
        ));
        final HashtagSaveResponse hashtagSaveResponse = new HashtagSaveResponse(hashtags);

        doReturn(OOTD).when(ootdService).saveOotd(any());
        doReturn(pictures).when(pictureService).savePictures(any());
        doNothing().when(ootdPictureService).saveOotdPictures(any());
        doReturn(hashtagSaveResponse).when(hashtagService).saveHashtags(any());
        doNothing().when(ootdHashtagService).saveOotdHashtags(any());

        final MockMultipartFile picture1 = new MockMultipartFile(
                "picture1", "pngPicture.png",
                MediaType.MULTIPART_FORM_DATA_VALUE, "testPngPicture".getBytes());

        final MockMultipartFile picture2 = new MockMultipartFile(
                "picture2", "pngPicture.png",
                MediaType.MULTIPART_FORM_DATA_VALUE, "testPngPicture".getBytes());

        final MockMultipartFile jsonRequest = new MockMultipartFile(
                "request", "", MediaType.APPLICATION_JSON_VALUE,
                ("{" +
                        "\"nickname\": \"닉네임\"" +
                        "\"content\": \"내용\"" +
                        "\"top\": \"상의\"" +
                        "\"bottom\": \"하의\"" +
                        "\"shoes\": \"신발\"" +
                        "\"accessory\": \"악세사리\"" +
                        "\"hashtags\": [\"해시태그1\", \"해시태그2\"]" +
                        "}").getBytes());

        mockMvc.perform(multipart("/api/ootds")
                .file("pictures", picture1.getBytes())
                .file("pictures", picture2.getBytes())
                .file("request", jsonRequest.getBytes())
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .accept(MediaType.MULTIPART_FORM_DATA)
                .characterEncoding("UTF-8"))
                .andExpect(status().isNoContent());
    }
}
