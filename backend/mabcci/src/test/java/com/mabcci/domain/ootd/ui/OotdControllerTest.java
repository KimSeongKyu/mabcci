package com.mabcci.domain.ootd.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.application.OotdDeleteService;
import com.mabcci.domain.ootd.application.OotdFindService;
import com.mabcci.domain.ootd.application.OotdSaveService;
import com.mabcci.domain.ootd.application.OotdUpdateService;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootd.dto.request.OotdUpdateRequest;
import com.mabcci.domain.ootd.dto.response.OotdDetailResponse;
import com.mabcci.domain.ootd.dto.response.OotdListResponse;
import com.mabcci.domain.ootdpicture.domain.OotdPicture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OotdController.class)
class OotdControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private OotdSaveService ootdSaveService;
    @MockBean
    private OotdFindService ootdFindService;
    @MockBean
    private OotdUpdateService ootdUpdateService;
    @MockBean
    private OotdDeleteService ootdDeleteService;

    private Member member;
    private Ootd ootd;

    @BeforeEach
    void setUp() {
        member = Member.Builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(Gender.MAN)
                .description(DESCRIPTION)
                .picture(PICTURE)
                .memberRole(MemberRole.USER)
                .build();
        ootd = Ootd.builder()
                .member(member)
                .content("content")
                .top("top")
                .bottom("bottom")
                .shoes("shoes")
                .accessory("accessory")
                .views(0L)
                .build();
    }

    @DisplayName("OotdController 인스턴스 ootd 등록 API 테스트")
    @Test
    void register_ootd_test() throws Exception {
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

        doNothing().when(ootdSaveService).saveOotdAndPicturesAndHashtags(any());

        mockMvc.perform(multipart("/api/ootds")
                .file("pictures", picture1.getBytes())
                .file("pictures", picture2.getBytes())
                .file("request", jsonRequest.getBytes())
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .accept(MediaType.MULTIPART_FORM_DATA)
                .characterEncoding("UTF-8"))
                .andExpect(status().isNoContent());
    }

    @DisplayName("OotdController 인스턴스 ootd 상세 조회 API 테스트")
    @Test
    void find_ootd_detail_test() throws Exception {
        final OotdPicture ootdPicture = OotdPicture.builder()
                        .ootd(ootd)
                        .url("testUrl")
                        .fileName("testFileName")
                        .build();
        ReflectionTestUtils.setField(ootd, "ootdPictures", List.of(ootdPicture));
        final OotdDetailResponse ootdDetailResponse = OotdDetailResponse.ofOotd(ootd);

        doReturn(ootdDetailResponse).when(ootdFindService).findOotdDetail(any());

        mockMvc.perform(get("/api/ootds/{id}", 1)
                .content(objectMapper.writeValueAsString(ootdDetailResponse))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("OotdController 인스턴스 필터링된 ootd 리스트 조회 API 테스트")
    @Test
    void find_filtered_ootd_list_test() throws Exception {
        final OotdListResponse ootdListResponse = new OotdListResponse(new ArrayList<>(), 1);

        doReturn(ootdListResponse).when(ootdFindService).findOotds(any(), any(), any());

        mockMvc.perform(get("/api/ootds/{nickname}", "닉네임")
                .param("filter", "all")
                .param("size", "20")
                .param("page", "0")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("OotdController 인스턴스 ootd 수정 API 테스트")
    @Test
    void update_ootd_test() throws Exception {
        final OotdUpdateRequest ootdUpdateRequest =
                new OotdUpdateRequest("내용", "상의", "하의", "신발", "악세사리");

        doNothing().when(ootdUpdateService).updateOotd(any(), any());

        mockMvc.perform(put("/api/ootds/" + 1L)
                .content(objectMapper.writeValueAsString(ootdUpdateRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @DisplayName("OotdController 인스턴스 ootd 삭제 API 테스트")
    @Test
    void delete_ootd_test() throws Exception {
        doNothing().when(ootdDeleteService).deleteOotdById(any());

        mockMvc.perform(delete("/api/ootds/" + 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}

