package com.mabcci.domain.ootdcomment.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mabcci.domain.ootdcomment.application.OotdCommentSaveService;
import com.mabcci.domain.ootdcomment.dto.request.OotdCommentSaveRequest;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OotdCommentController.class)
class OotdCommentControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private OotdCommentSaveService ootdCommentSaveService;

    @DisplayName("OotdCommentController 인스턴스 ootd 댓글 저장 API 테스트")
    @Test
    void save_ootd_comment_api_test() throws Exception {
        final OotdCommentSaveRequest ootdCommentSaveRequest =
                new OotdCommentSaveRequest(1L, NICKNAME, null, "내용");

        doNothing().when(ootdCommentSaveService).saveOotdComment(any());

        mockMvc.perform(post("/api/ootd/comments")
                .content(objectMapper.writeValueAsString(ootdCommentSaveRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
