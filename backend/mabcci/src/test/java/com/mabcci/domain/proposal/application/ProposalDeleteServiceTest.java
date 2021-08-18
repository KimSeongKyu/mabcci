package com.mabcci.domain.proposal.application;

import com.mabcci.domain.proposal.domain.ProposalRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProposalDeleteServiceTest {

    @InjectMocks private ProposalDeleteService proposalDeleteService;
    @Mock private ProposalRepository proposalRepository;

    @DisplayName("ProposalDeleteService 인스턴스 제안서 삭제 테스트")
    @Test
    void delete_proposal_by_id_test() {
        doNothing().when(proposalRepository).deleteById(any());

        proposalDeleteService.deleteProposalById(1L);

        verify(proposalRepository, times(1)).deleteById(any());
    }

}
