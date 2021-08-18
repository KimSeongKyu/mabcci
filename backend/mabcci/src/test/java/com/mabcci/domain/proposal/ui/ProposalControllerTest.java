package com.mabcci.domain.proposal.ui;

import com.mabcci.domain.proposal.application.ProposalDeleteService;
import com.mabcci.domain.proposal.application.ProposalFindService;
import com.mabcci.domain.proposal.application.ProposalSaveService;
import com.mabcci.domain.proposal.domain.ProposalFilter;
import com.mabcci.domain.proposal.dto.response.ProposalDetailFindResponse;
import com.mabcci.domain.proposal.dto.response.ProposalFindResponse;
import com.mabcci.domain.proposal.dto.response.ProposalFindResponses;
import com.mabcci.global.common.Nickname;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProposalController.class)
class ProposalControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private ProposalSaveService proposalSaveService;
    @MockBean private ProposalFindService proposalFindService;
    @MockBean private ProposalDeleteService proposalDeleteService;

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

    @DisplayName("ProposalController 인스턴스 제안서 리스트 조회 API 테스트")
    @Test
    void find_proposals_api_test() throws Exception {
        final ProposalFindResponse proposalFindResponse = new ProposalFindResponse(1L, PICTURE, NICKNAME, LocalDateTime.now(), false);
        final ProposalFindResponses proposalFindResponses = new ProposalFindResponses(List.of(proposalFindResponse));

        doReturn(proposalFindResponses).when(proposalFindService).findProposals(any(), any());

        mockMvc.perform(get("/api/proposals?filter={filter}&nickname={nickname}", ProposalFilter.SUGGESTED, NICKNAME)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("ProposalController 인스턴스 제안서 상세 조회 API 테스트")
    @Test
    void find_proposal_api_test() throws Exception {
        final ProposalDetailFindResponse proposalDetailFindResponse =
                new ProposalDetailFindResponse(Nickname.of("targetMember"), Nickname.of("mabcci"), null, null, null, null, null);

        doReturn(proposalDetailFindResponse).when(proposalFindService).findProposal(any());

        mockMvc.perform(get("/api/proposals/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("ProposalController 인스턴스 제안서 삭제 API 테스트")
    @Test
    void delete_proposal_api_test() throws Exception {
        doNothing().when(proposalDeleteService).deleteProposalById(any());

        mockMvc.perform(delete("/api/proposals/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
