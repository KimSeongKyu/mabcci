package com.mabcci.domain.proposalreview.application;

import com.mabcci.domain.proposalreview.domain.ProposalReview;
import com.mabcci.domain.proposalreview.domain.ProposalReviewRepository;
import com.mabcci.domain.proposalreview.dto.response.ProposalReviewFindResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class ProposalReviewFindService {

    private final ProposalReviewRepository proposalReviewRepository;

    public ProposalReviewFindService(final ProposalReviewRepository proposalReviewRepository) {
        this.proposalReviewRepository = proposalReviewRepository;
    }

    public ProposalReviewFindResponse findProposalReviewByProposalId(final Long id) {
        return ProposalReviewFindResponse.ofProposalReview(getProposalReviewByProposalId(id));
    }

    private ProposalReview getProposalReviewByProposalId(final Long id) {
        return proposalReviewRepository.findByProposalId(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}
