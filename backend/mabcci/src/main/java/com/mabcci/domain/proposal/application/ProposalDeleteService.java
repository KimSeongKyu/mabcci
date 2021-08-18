package com.mabcci.domain.proposal.application;

import com.mabcci.domain.proposal.domain.ProposalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProposalDeleteService {

    private final ProposalRepository proposalRepository;

    public ProposalDeleteService(final ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    @Transactional
    public void deleteProposalById(final Long id) {
        proposalRepository.deleteById(id);
    }
}
