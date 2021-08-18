package com.mabcci.domain.proposal.application;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.proposal.domain.Proposal;
import com.mabcci.domain.proposal.domain.ProposalFilter;
import com.mabcci.domain.proposal.domain.ProposalRepository;
import com.mabcci.domain.proposal.dto.response.ProposalDetailFindResponse;
import com.mabcci.domain.proposal.dto.response.ProposalFindResponse;
import com.mabcci.domain.proposal.dto.response.ProposalFindResponses;
import com.mabcci.global.common.Nickname;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Transactional(readOnly = true)
@Service
public class ProposalFindService {

    private final ProposalRepository proposalRepository;

    public ProposalFindService(final ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    public ProposalFindResponses findProposals(final ProposalFilter proposalFilter, final Nickname nickname) {
        final List<Proposal> proposals = proposalFilter.getFilteredProposals(proposalRepository, nickname);
        return new ProposalFindResponses(mapProposalsToFindResponses(proposals, proposalFilter));
    }

    private List<ProposalFindResponse> mapProposalsToFindResponses(final List<Proposal> proposals, final ProposalFilter proposalFilter) {
        return proposals.stream()
                .map(proposal -> ProposalFindResponse.ofProposalAndMember(proposal, getMemberAccordingToFilter(proposal, proposalFilter)))
                .collect(toList());
    }

    private Member getMemberAccordingToFilter(final Proposal proposal, final ProposalFilter proposalFilter) {
        if(proposalFilter.equals(ProposalFilter.RECEIVED)) {
            return proposal.mabcci();
        }
        return proposal.targetMember();
    }

    public ProposalDetailFindResponse findProposal(final Long id) {
        return ProposalDetailFindResponse.ofProposal(getProposalById(id));
    }

    private Proposal getProposalById(final Long id) {
        return proposalRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}
