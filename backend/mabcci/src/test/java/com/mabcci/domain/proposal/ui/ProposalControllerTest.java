package com.mabcci.domain.proposal.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mabcci.domain.proposal.application.ProposalSaveService;
import com.mabcci.domain.proposal.dto.ProposalSaveRequest;
import com.mabcci.global.common.Nickname;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.mabcci.domain.picture.common.PictureUtilTest.PICTURE_FILES;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProposalController.class)
class ProposalControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private ProposalSaveService proposalSaveService;

    @DisplayName("ProposalController 인스턴스 제안서 저장 API 테스트")
    @Test
    void save_proposal_api_test() throws Exception {
        final ProposalSaveRequest proposalSaveRequest = new ProposalSaveRequest(Nickname.of("targetMember"), Nickname.of("mabcci"),
                PICTURE_FILES.get(0), PICTURE_FILES.get(1), null, null, "설명");
        doNothing().when(proposalSaveService).saveProposal(any());

        mockMvc.perform(post("/api/proposal")
                .content(objectMapper.writeValueAsString(proposalSaveRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
