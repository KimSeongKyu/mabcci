package com.mabcci.domain.ootdLike.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mabcci.domain.ootdLike.application.OotdLikeSaveAndUpdateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OotdLikeController.class)
class OotdLikeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private OotdLikeSaveAndUpdateService ootdLikeSaveAndUpdateService;

    @DisplayName("OotdLikeController 인스턴스 좋아요 생성 및 수정 API 테스트")
    @Test
    void save_or_update_ootd_like_api_test() throws Exception {
        doNothing().when(ootdLikeSaveAndUpdateService).saveOrUpdateOotdLike(any(), any());

        mockMvc.perform(post("/api/ootd/{id}/like/{nickname}", 1, "nickname")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}
