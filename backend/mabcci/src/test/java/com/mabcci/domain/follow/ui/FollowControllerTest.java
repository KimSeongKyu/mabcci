package com.mabcci.domain.follow.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mabcci.domain.follow.application.FollowService;
import com.mabcci.domain.follow.dto.FollowSaveRequest;
import com.mabcci.global.common.Nickname;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class FollowControllerTest {

    @Mock private FollowService followService;
    @InjectMocks private FollowController followController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(followController).build();
        objectMapper = new ObjectMapper();
    }

    @DisplayName("FollowController 인스턴스 생성 여부 테스트")
    @Test
    void constructor_test() {
        final FollowController followController = new FollowController(followService);

        assertAll(
                () -> assertThat(followController).isNotNull(),
                () -> assertThat(followController).isExactlyInstanceOf(FollowController.class)
        );
    }

    @DisplayName("FollowController 인스턴스 팔로우 등록 기능 테스트")
    @Test
    void save_test() throws Exception {
        given(followService.save(any(), any())).willReturn(1L);
        final Nickname following = Nickname.of("following");
        final Nickname follower = Nickname.of("follower");
        final FollowSaveRequest followSaveRequest = new FollowSaveRequest(following, follower);
        final String followSaveRequestString = objectMapper.writeValueAsString(followSaveRequest);

        mockMvc.perform(post("/api/follow")
                .contentType(MediaType.APPLICATION_JSON)
                .content(followSaveRequestString))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @DisplayName("FollowController 인스턴스 팔로우 취소 기능 테스트")
    @Test
    void cancel_test() throws Exception {
        doNothing().when(followService).cancel(any());

        mockMvc.perform(delete("/api/follow")
                .contentType(MediaType.APPLICATION_JSON)
                .content("1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}