package com.mabcci.domain.ootd.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mabcci.domain.hashtag.application.HashtagService;
import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.hashtag.dto.HashtagRegisterResponse;
import com.mabcci.domain.member.application.MemberService;
import com.mabcci.domain.ootd.application.OotdService;
import com.mabcci.domain.ootd.dto.OotdRegisterWithPicturesAndHashtagsRequest;
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
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static com.mabcci.domain.picture.common.PictureUtilTest.PICTURE_FILES;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OotdController.class)
class OotdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

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
        final HashtagRegisterResponse hashtagRegisterResponse = new HashtagRegisterResponse(hashtags);

        doReturn(MEMBER).when(memberService).findByNickName(any());
        doNothing().when(ootdService).saveOotd(any());
        doReturn(pictures).when(pictureService).savePictures(any());
        doNothing().when(ootdPictureService).registerOotdPictures(any());
        doReturn(hashtagRegisterResponse).when(hashtagService).saveHashtags(any());
        doNothing().when(ootdHashtagService).saveOotdHashtags(any());

        final OotdRegisterWithPicturesAndHashtagsRequest request =
                new OotdRegisterWithPicturesAndHashtagsRequest(
                        "닉네임", "내용", "상의", "하의", "신발", "악세사리",
                        PICTURE_FILES, new ArrayList<>(List.of("해시태그1", "해시태그2"))
                );

        mockMvc.perform(post("/api/ootds")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
