package com.mabcci.domain.ootdcomment.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mabcci.domain.ootdcomment.application.OotdCommentDeleteService;
import com.mabcci.domain.ootdcomment.application.OotdCommentSaveService;
import com.mabcci.domain.ootdcomment.application.OotdCommentUpdateService;
import com.mabcci.domain.ootdcomment.dto.request.OotdCommentSaveRequest;
import com.mabcci.domain.ootdcomment.dto.request.OotdCommentUpdateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OotdCommentController.class)
class OotdCommentControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private OotdCommentSaveService ootdCommentSaveService;
    @MockBean private OotdCommentUpdateService ootdCommentUpdateService;
    @MockBean private OotdCommentDeleteService ootdCommentDeleteService;

    @DisplayName("OotdCommentController 인스턴스 ootd 댓글 저장 API 테스트")
    @Test
    void save_ootd_comment_api_test() throws Exception {
        final OotdCommentSaveRequest ootdCommentSaveRequest =
                new OotdCommentSaveRequest(1L, NICKNAME, null, "내용");

        doNothing().when(ootdCommentSaveService).saveOotdComment(any());

        mockMvc.perform(post("/api/ootd/comments")
                .content(objectMapper.writeValueAsString(ootdCommentSaveRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @DisplayName("OotdCommentController 인스턴스 ootd 댓글 수정 API 테스트")
    @Test
    void update_ootd_comment_api_test() throws Exception {
        final OotdCommentUpdateRequest ootdCommentUpdateRequest = new OotdCommentUpdateRequest(NICKNAME, "수정된 내용");

        doNothing().when(ootdCommentUpdateService).updateOotdComment(any(), any());

        mockMvc.perform(put("/api/ootd/comments/" + 1)
                .content(objectMapper.writeValueAsString(ootdCommentUpdateRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @DisplayName("OotdCommentController 인스턴스 ootd 댓글 삭제 API 테스트")
    @Test
    void delete_ootd_comment_api_test() throws Exception {
        doNothing().when(ootdCommentDeleteService).deleteOotdComment(any());

        mockMvc.perform(delete("/api/ootd/comments/" + 1))
                .andExpect(status().isNoContent());
    }
}
