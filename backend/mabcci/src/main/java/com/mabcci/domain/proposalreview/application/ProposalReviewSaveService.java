package com.mabcci.domain.proposalreview.application;

import com.mabcci.domain.proposal.domain.Proposal;
import com.mabcci.domain.proposal.domain.ProposalRepository;
import com.mabcci.domain.proposalreview.domain.ProposalReview;
import com.mabcci.domain.proposalreview.domain.ProposalReviewRepository;
import com.mabcci.domain.proposalreview.domain.StarRating;
import com.mabcci.domain.proposalreview.dto.request.ProposalReviewSaveRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProposalReviewSaveService {

    private final ProposalReviewRepository proposalReviewRepository;
    private final ProposalRepository proposalRepository;

    public ProposalReviewSaveService(final ProposalReviewRepository proposalReviewRepository,
                                     final ProposalRepository proposalRepository) {
        this.proposalReviewRepository = proposalReviewRepository;
        this.proposalRepository = proposalRepository;
    }

    @Transactional
    public void saveProposalReview(final ProposalReviewSaveRequest proposalReviewSaveRequest) {
        final Proposal proposal = getProposalById(proposalReviewSaveRequest.id());
        final ProposalReview proposalReview = buildProposalReview(proposal,
                proposalReviewSaveRequest.starRating(), proposalReviewSaveRequest.content());
        saveProposalReview(proposalReview);
    }

    private void saveProposalReview(final ProposalReview proposalReview) {
        proposalReviewRepository.save(proposalReview);
    }

    private ProposalReview buildProposalReview(final Proposal proposal, final StarRating starRating, final String content) {
        return ProposalReview.builder()
                .proposal(proposal)
                .starRating(starRating)
                .content(content)
                .build();
    }

    private Proposal getProposalById(final Long id) {
        return proposalRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}
