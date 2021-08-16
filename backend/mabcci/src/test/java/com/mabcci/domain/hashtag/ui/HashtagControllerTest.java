package com.mabcci.domain.hashtag.ui;

import com.mabcci.domain.hashtag.application.HashtagFindService;
import com.mabcci.domain.hashtag.dto.response.HashtagsFindByNameContainsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HashtagController.class)
class HashtagControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private HashtagFindService hashtagFindService;

    @DisplayName("HashtagController 인스턴스 키워드를 이름에 포함한 해시태그 리스트 검색 API 테스트")
    @Test
    void find_by_name_contains_api_test() throws Exception {
        final HashtagsFindByNameContainsResponse hashtagsFindByNameContainsResponse =
                new HashtagsFindByNameContainsResponse(Collections.emptyList());

        doReturn(hashtagsFindByNameContainsResponse).when(hashtagFindService).findByNameContains(any());

        mockMvc.perform(get("/api/hashtags/search?hashtag={hashtag}", "해시태그")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
