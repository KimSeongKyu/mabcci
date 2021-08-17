package com.mabcci.domain.proposal.ui;

import com.mabcci.domain.proposal.application.ProposalSaveService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProposalController.class)
class ProposalControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProposalSaveService proposalSaveService;

    @DisplayName("ProposalController 인스턴스 제안서 저장 API 테스트")
    @Test
    void save_proposal_api_test() throws Exception {
        final MockMultipartFile topPicture = new MockMultipartFile(
                "topPicture", "pngPicture.png",
                MediaType.MULTIPART_FORM_DATA_VALUE, "testPngPicture".getBytes());

        final MockMultipartFile bottomPicture = new MockMultipartFile(
                "bottomPicture", "pngPicture.png",
                MediaType.MULTIPART_FORM_DATA_VALUE, "testPngPicture".getBytes());

        final MockMultipartFile jsonRequest = new MockMultipartFile(
                "request", "", MediaType.APPLICATION_JSON_VALUE,
                ("{" +
                        "\"targetMemberNickname\": \"제안서를 신청한 멤버 닉네임\"" +
                        "\"mabcciNickname\": \"맵씨 닉네임\"" +
                        "\"top\": \"상의\"" +
                        "\"bottom\": \"하의\"" +
                        "\"shoes\": \"신발\"" +
                        "\"accessory\": \"악세사리\"" +
                        "\"description\": \"설명\"" +
                        "}").getBytes());
        doNothing().when(proposalSaveService).saveProposal(any());

        mockMvc.perform(multipart("/api/proposals")
                .file("topPicture", topPicture.getBytes())
                .file("bottomPicture", bottomPicture.getBytes())
                .file("request", jsonRequest.getBytes())
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .accept(MediaType.MULTIPART_FORM_DATA)
                .characterEncoding("UTF-8"))
                .andExpect(status().isNoContent());
    }
}
